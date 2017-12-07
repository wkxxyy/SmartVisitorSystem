package com.smartvisitorsystem.android.VisitorRegistration.SignOut;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.Util.AddVisitor;
import com.smartvisitorsystem.android.Util.SignOutVisitor;
import com.smartvisitorsystem.android.VisitorRegistration.Gson.Result;
import com.smartvisitorsystem.android.VisitorRegistration.IdcardResActivity;
import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.Youtu;
import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.common.Config;
import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.common.ImageUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.common.Config.SECRET_ID;

public class IdCardSignOutActivity extends AppCompatActivity {

    EditText idCradSignOutNameText;

    Button idCardSignOutTakePicture;
    Button idCardSignOutConfirm;

    ImageView idCardSignOutImageView;



    private static final int CHOOSE_PHOTO = 0;
    private static final int TAKE_PHOTO = 1;

    private ProgressDialog pd;

    private List<String> permissionList;

    private Uri imageUri;

    private static final String TAG = "IdCardSignOutActivity";

    String message=null;
    String picPath = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card_sign_out);

        idCradSignOutNameText=(EditText)findViewById(R.id.idcard_sign_out_name_text);

        idCardSignOutTakePicture=(Button)findViewById(R.id.idcard_sign_out_take_picture);
        idCardSignOutConfirm=(Button)findViewById(R.id.idcard_sign_out_confirm);

        idCardSignOutImageView=(ImageView)findViewById(R.id.idcard_sign_out_image_view);

        idCardSignOutTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionList=new ArrayList<>();//请求权限
                if (ContextCompat.checkSelfPermission(IdCardSignOutActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.CAMERA);
                }
                if (ContextCompat.checkSelfPermission(IdCardSignOutActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (!permissionList.isEmpty()){
                    String[] permissions=permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(IdCardSignOutActivity.this,permissions,1);
                }
                else {
                    Log.d(TAG, "1");
                    takePhoto();
                }
            }
        });

        idCardSignOutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idCardNameStrig=idCradSignOutNameText.getText().toString();
                System.out.println(TAG+"222222222222");
                boolean isSucceed= SignOutVisitor.SignOutVisitor(idCardNameStrig);
                if (isSucceed){
                    Toast.makeText(IdCardSignOutActivity.this,"签退成功",Toast.LENGTH_SHORT).show();
                    System.out.println(TAG+"3333333333333333");
                }else {
                    Toast.makeText(IdCardSignOutActivity.this,"签退失败",Toast.LENGTH_SHORT).show();
                    System.out.println(TAG+"4444444444444444");
                }
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    displayImage(picPath);
                    pd = ProgressDialog.show(IdCardSignOutActivity.this, "", "正在识别请稍后......");
                    testImageOcr();
                    Log.d(TAG, "识别");
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
            idCardSignOutImageView.setImageBitmap(btp);
        } else {
            Toast.makeText(IdCardSignOutActivity.this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:{//拍照权限
                if (grantResults.length > 0) {
                    for (int result:grantResults){
                        if(result!= PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    Log.d(TAG, "5");
                    takePhoto();
                } else {
                    Toast.makeText(IdCardSignOutActivity.this, "you denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            break;

            default:
                break;
        }


    }

    public void takePhoto() {
        Log.d(TAG, "6");
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
            imageUri = FileProvider.getUriForFile(IdCardSignOutActivity.this, "com.smartvisitorsystem.fileprovider", outputImage);
            //获取封装后的Uri
        } else {
            imageUri = Uri.fromFile(outputImage);//获取Uri
        }
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");//启动相机
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//能在这个程序里面使用这个Uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//让相机把图片存储在这个位置。
        startActivityForResult(intent, TAKE_PHOTO);
        Log.d(TAG, "完成");

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

    private void testImageOcr() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Youtu faceYoutu = new Youtu(Config.APP_ID, SECRET_ID, Config.SECRET_KEY, Youtu.API_YOUTU_END_POINT);//初始化身份证识别的youtu的apapid
                try {
                    Bitmap selectedImage = ImageUtil.ratio(BitmapFactory.decodeFile(picPath),1024,768);
                    final JSONObject respose = faceYoutu.IdcardOcr(selectedImage, 0);
                    message=respose.toString();
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          setResponseText();
                                      }
                                  }
                    );
                    if(null != selectedImage) {
                        selectedImage.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }//识别身份证

    private void testBcardOcr(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Youtu faceYoutu = new Youtu(Config.APP_ID, SECRET_ID, Config.SECRET_KEY, Youtu.API_YOUTU_END_POINT);
                try {
                    Bitmap selectedImage = BitmapFactory.decodeFile(picPath);
                    JSONObject respose = faceYoutu.NamecardOcr(selectedImage);
                    System.out.println("数据"+respose.toString());
                    if(null != selectedImage) {
                        selectedImage.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//识别名片

    private void setResponseText(){
        Gson gson=new Gson();

        Result result=gson.fromJson(message,Result.class);

        idCradSignOutNameText.setText(result.getName());

        pd.dismiss();

    }//设置信息


}


