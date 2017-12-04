package com.smartvisitorsystem.android.VisitorRegistration;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hanvon.HWCloudManager;
import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.Util.AddVisitor;
import com.smartvisitorsystem.android.VisitorRegistration.Gson.JSON;
import com.smartvisitorsystem.android.VisitorRegistration.Gson.Result;
import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.Youtu;
import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.common.Config;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.common.Config.SECRET_ID;

public class BcardResActivity extends AppCompatActivity{

    private static final int CHOOSE_PHOTO = 0;
    private static final int TAKE_PHOTO = 1;
    private static final int START_QUERY = 2;


    EditText bCardNameText;
    EditText bCardTelText;
    EditText bCardNoText;
    EditText bCardMessage;
    EditText bCardStaff;

    Button bCardTakePicture;
    Button bCardSaveMessage;

    ImageView bCardImageView;



    private ProgressDialog pd;

    private DiscernHandler discernHandler;

    private Uri imageUri;

    String picPath = null;

    String result = null;

    private HWCloudManager hwCloudManagerIdcard;//请求发起对象

    private List<String> permissionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regis_bcard_res);


        hwCloudManagerIdcard = new HWCloudManager(this, "fa72cd64-857b-4d7b-b44e-0e46de193545");
        discernHandler = new DiscernHandler();

        bCardNameText=(EditText)findViewById(R.id.bcard_name_text);
        bCardTelText=(EditText)findViewById(R.id.bcard_tel_text);
        bCardNoText=(EditText)findViewById(R.id.bcard_no_text);
        bCardMessage=(EditText)findViewById(R.id.bcard_message_text);
        bCardStaff=(EditText)findViewById(R.id.bcard_staff_text);

        bCardTakePicture=(Button)findViewById(R.id.bcard_take_picture);
        bCardSaveMessage=(Button)findViewById(R.id.bcard_save_message);

        bCardTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionList=new ArrayList<>();//请求权限
                if (ContextCompat.checkSelfPermission(BcardResActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.CAMERA);
                }
                if (ContextCompat.checkSelfPermission(BcardResActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (!permissionList.isEmpty()){
                    String[] permissions=permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(BcardResActivity.this,permissions,1);
                }
                else {
                    takePhoto();
                }
            }
        });

        bCardSaveMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bCardNameStrig=bCardNameText.getText().toString();
                String bCardTelString=bCardTelText.getText().toString();
                String bCardNoString=bCardNoText.getText().toString();
                String bCardMessageString=bCardMessage.getText().toString();
                String bCardStaffNameString=bCardStaff.getText().toString();

                boolean isSucceed= AddVisitor.addVisitor(bCardNameStrig,bCardNoString,bCardTelString,bCardMessageString,bCardStaffNameString);

                if (isSucceed){
                    Toast.makeText(BcardResActivity.this,"提交成功，正在打印凭条，请稍等",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BcardResActivity.this,"添加失败请重新添加",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bCardImageView=(ImageView)findViewById(R.id.bcard_image_view);

    }


    public class DiscernThread implements Runnable {

        public void run() {
            try {
                result = hwCloudManagerIdcard.cardLanguage("chns",picPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Bundle mBundle = new Bundle();
            mBundle.putString("responce", result);
            Message msg = new Message();
            msg.setData(mBundle);
            discernHandler.sendMessage(msg);

        }

    }

    public class DiscernHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pd.dismiss();
            Bundle bundle = msg.getData();
            String responce = bundle.getString("responce");
            System.out.println("json"+responce);
            parseJSON(responce);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    displayImage(picPath);
                    pd = ProgressDialog.show(BcardResActivity.this, "", "正在识别请稍后......");
                    DiscernThread discernThread = new DiscernThread();
                    new Thread(discernThread).start();
                }
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                picPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                picPath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            picPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            picPath = uri.getPath();
        } else {
            getImagePath(uri, null);

        }
        displayImage(picPath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        picPath = getImagePath(uri, null);
        displayImage(picPath);
    }

    private String getImagePath(Uri uri, String select) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, select, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {

            InputStream is = null;
            try {
                is = new FileInputStream(picPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inJustDecodeBounds = false;

            options.inSampleSize = 10;   // width，hight设为原来的十分一

            Bitmap btp = BitmapFactory.decodeStream(is, null, options);
            //Bitmap bitmap =BitmapFactory.decodeFile(imagePath);
            bCardImageView.setImageBitmap(btp);
        } else {
            Toast.makeText(BcardResActivity.this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:{//选择图片权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(BcardResActivity.this, "you denied the permission", Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case 1:{//拍照权限
                if (grantResults.length > 0) {
                    for (int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    takePhoto();
                } else {
                    Toast.makeText(BcardResActivity.this, "you denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            break;

            default:
                break;
        }


    }

    public void takePhoto() {
        final String CACHE_IMG = Environment.getExternalStorageDirectory() + "/demo/";//获取sd卡路径
        String fileName = "defaultImage.jpg";//存储在存储卡里面
        File outputImage = new File(CACHE_IMG, fileName);//创建存储照片的对象
        //File outputImage=new File(getExternalCacheDir(),"output_image.jpg");//存储在关联目录里面，可以跳过权限申请。
        picPath = outputImage.getPath();//得到拍照的真实路径
        try {
            if (!outputImage.exists()) {
                outputImage.mkdirs();
            }
            if ((outputImage.exists())) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(BcardResActivity.this, "com.smartvisitorsystem.fileprovider", outputImage);
            //获取封装后的Uri
        } else {
            imageUri = Uri.fromFile(outputImage);//获取Uri
        }
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");//启动相机
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//能在这个程序里面使用这个Uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//让相机把图片存储在这个位置。
        startActivityForResult(intent, TAKE_PHOTO);

    }

    private void openAlbum() {
        //Intent intent=new Intent();
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.setAction("android.intent.action.GET_CONTENT");//调用文件管理器的样子
        //intent.setType("image/*");

        //Intent intent = new Intent();//打开相册的样子
        //intent.setAction(Intent.ACTION_PICK);
        //intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    public void parseJSON(String jsonData){

        JSON jsonGetText=new JSON(jsonData);

        bCardNameText.setText(jsonGetText.getJSONText("name"));

        bCardTelText.setText(jsonGetText.getJSONText("mobile"));


    }
}
