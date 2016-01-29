package com.example.project.UnityLiving.fragment;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.project.UnityLiving.R;
import com.example.project.UnityLiving.model.ApartmentModel;

import java.io.File;
import java.util.ArrayList;

import eu.janmuller.android.simplecropimage.CropImage;

public class CreateRequestFragment extends Fragment implements View.OnClickListener {
    public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Bitmap bitmap = null;
    private File mFileTemp;

    private ArrayList<ApartmentModel> mItems;
    private ArrayList<String> mApartmentName;
    private View root;
    private ImageButton mCameraButton, mDoneButton, mCancelButton;
    private ImageView mUserImage;
    private EditText mNameEditText, mPhoneEditText, mAppartmentEditText;

    private Activity mContext;
    private AlertDialog.Builder ab;

    public CreateRequestFragment() {
        // Required empty public constructor
    }

    public static CreateRequestFragment newInstance(ArrayList<ApartmentModel> mItems) {
        CreateRequestFragment fragment = new CreateRequestFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, mItems);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItems = (ArrayList<ApartmentModel>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_create_request, container, false);
        mContext = getActivity();
        initViews();
        setApartments();
        tempSetting();
        return root;
    }

    private void setApartments() {
        mApartmentName = new ArrayList<>();
        for (ApartmentModel mItem : mItems) {
            mApartmentName.add(mItem.mAppartmentName);
        }
        ab = new AlertDialog.Builder(mContext);
        ab.setTitle("Select apartment");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.select_dialog_item, mApartmentName);
        ab.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAppartmentEditText.setText(mApartmentName.get(which));
            }
        });

    }

    private void initViews() {
        mCameraButton = (ImageButton) root.findViewById(R.id.camera);
        mUserImage = (ImageView) root.findViewById(R.id.user_image);
        mNameEditText = (EditText) root.findViewById(R.id.user_name);
        mPhoneEditText = (EditText) root.findViewById(R.id.password);
        mAppartmentEditText = (EditText) root.findViewById(R.id.appartment);
        mAppartmentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ab.show();
                }
            }
        });
        mAppartmentEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ab.show();

            }
        });

        mCameraButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                takeImageFromCamera();
                break;
        }
    }


    private void tempSetting() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(mContext.getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }
    }

    /**
     * capture image using Camera
     */
    public void takeImageFromCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);

            } else {
                /*
                 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
				 */
                //  mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PICTURE:
                startCropImage();
                break;

            case REQUEST_CODE_CROP_IMAGE:
                if (data == null) return;
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {
                    return;
                }
                bitmap = BitmapFactory.decodeFile(mFileTemp.getPath());
                if (bitmap != null)
                    mUserImage.setImageBitmap(bitmap);
                break;
        }
    }


    private void startCropImage() {
        Intent intent = new Intent(mContext, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.ASPECT_X, 2);
        intent.putExtra(CropImage.ASPECT_Y, 2);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }


}
