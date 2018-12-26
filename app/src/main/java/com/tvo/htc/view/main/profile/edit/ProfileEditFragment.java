package com.tvo.htc.view.main.profile.edit;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.lib.model.response.CityResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.util.RealFilePath;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CircleImageView;
import com.tvo.htc.view.component.CpnEditText;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.dialog.ConfirmDialog;
import com.tvo.htc.view.main.MainActivity;
import com.tvo.htc.view.main.login.LoginFragment;
import com.tvo.htc.view.main.profile.ChangeAvatarDialog;
import com.tvo.htc.view.main.profile.car.add_car.listener.DateWatcher;
import com.tvo.htc.view.main.profile.edit.crop.CropFragment;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileEditFragment extends BaseFragment<ProfileEditContract.Presenter> implements ProfileEditContract.View, CropFragment.CallBack {
    public static final int REQ_GALLERY = 1010;
    public static final int REQ_CAMERA = 1111;
    public static final int REQ_CROP = 1212;
    public static final int REQ_PERMISSION = 13123;
    public static final String KEY_TYPE = "KEY_TYPE";
    public static final int TYPE_CONFIRM_LOGIN = 0;
    public static final int TYPE_PROFILE = 1;

    @BindView(R.id.edtName)
    CpnEditText edtName;
    @BindView(R.id.editPhone)
    CpnEditText editPhone;
    @BindView(R.id.editBirthDay)
    CpnEditText editBirthDay;
    @BindView(R.id.editIdentifyId)
    CpnEditText editIdentifyId;
    @BindView(R.id.editEmail)
    CpnEditText editEmail;
    @BindView(R.id.spCity)
    CpnSpinner spCity;
    @BindView(R.id.cpnbCircleImage)
    CircleImageView cpnbCircleImage;

    public static ProfileEditFragment newInstanceConfirmLogin() {
        return newInstance(TYPE_CONFIRM_LOGIN);
    }

    public static ProfileEditFragment newInstanceProfile() {
        return newInstance(TYPE_PROFILE);
    }

    static ProfileEditFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        ProfileEditFragment fragment = new ProfileEditFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ProfileEditContract.Presenter createPresenterInstance() {
        return new ProfileEditPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_edit;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        int type = getArguments().getInt(KEY_TYPE, TYPE_PROFILE);
        switch (type) {
            case TYPE_CONFIRM_LOGIN:
                setTitle(getString(R.string.profile_edit_title_confirm));
                mNavigationBar.setBackVisibility(View.INVISIBLE);
                mNavigationBar.setBtCancelVisibility(View.VISIBLE);
                mNavigationBar.setBtCancelText(getString(R.string.profile_edit_cancel));
                break;
        }
        editBirthDay.addTextWatcher(new DateWatcher(editBirthDay.getEditText()));
        getPresenter().loadData(type);
    }

    @Override
    protected boolean isShowGridBackground() {
        return true;
    }

    @Override
    public void displayInfo(String imagePath, String name, String phone, String dateOfBirth, String identityCard, String emailAddress, List<CityResponse.City> cities, int positionOfCity) {
        ImageLoader.loadImage(getContext(), cpnbCircleImage, R.drawable.img_avatar_default, imagePath, true);
        edtName.setText(name);
        editPhone.setText(phone);
        editBirthDay.setText(dateOfBirth);
        editIdentifyId.setText(identityCard);
        editEmail.setText(emailAddress);
        spCity.setData(cities, true);
        spCity.setSelection(positionOfCity);
    }

    @Override
    public void showConfirmSaveDialog() {
        ConfirmDialog dialog = new ConfirmDialog.Builder().setTextMessage(getString(R.string.profile_confirm_exit)).create();
        dialog.addListener(new ConfirmDialog.Callback() {
            @Override
            public void onConfirmClicked() {
                saveProfile(false);
            }

            @Override
            public void onCancelClicked() {
                getPresenter().handleFinish();
            }
        });
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void showError(String message) {
        showMessage(message, () -> getPresenter().handleFinish());
    }

    @Override
    public void showErrorEmailValid() {
        showMessage(getString(R.string.profile_info_error_email));
    }

    @Override
    public void showErrorIdentifyIdValid() {
        showMessage(getString(R.string.profile_info_error_indentify));
    }

    @Override
    public void showErrorLogin() {
        showMessage(getString(R.string.profile_error_get_profile), () -> {
            FragmentUtil.removeFragment(getContext());
            FragmentUtil.startFragmentNoTabbar(getActivity(), LoginFragment.newInstance(), null);
        });
    }

    @Override
    public void showMessageError(String message) {
        showMessage(message);
    }

    @Override
    public void showErrorEmptyName() {
        showMessage(getString(R.string.profile_error_empty_name));
    }

    @Override
    public void showErrorNameValid() {
        showMessage(getString(R.string.profile_error_invalid_name));
    }

    @Override
    public void showErrorBirthdayEmpty() {
        showMessage(getString(R.string.profile_error_empty_birthday));
    }

    @Override
    public void showErrorBirthdayValidate() {
        showMessage(getString(R.string.profile_error_birthday_validate));
    }

    @Override
    public void showErrorAddress() {
        showMessage(getString(R.string.profile_error_address));
    }

    @Override
    public void showWaitDialog(boolean isShowing) {
        if (isShowing) {
            showWaitDialog();
        } else {
            dismissWaitDialog();
        }
    }

    @Override
    public void finish() {
        FragmentUtil.removeFragment(getActivity());
    }

    @Override
    public void goToHome() {
        FragmentUtil.clearAllBackStack(getActivity());
        ((MainActivity) getActivity()).onTabMenuSelected(CpnTab.TabType.HOME);
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_BACK:
                saveProfile(true);
                break;
            case BT_CANCEL:
                saveProfile(true);
                break;
            default:
                super.onNavigationBtClick(type);
        }
    }

    @OnClick({R.id.flChangeAvatar, R.id.cpnbSave})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.flChangeAvatar:
                if (!PermissionUtil.checkReadWriteExternal(this, REQ_PERMISSION)) {
                    showChangeAvatar();
                }
                break;
            case R.id.cpnbSave:
                saveProfile(false);
                break;
        }
    }


    @Override
    public void croped(String path) {
        path = RealFilePath.formatPath(path);
        ImageLoader.loadImage(getContext(), cpnbCircleImage, path);
        getPresenter().changePathAvatar(path);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQ_GALLERY:
                    RealFilePath.cropImage(ProfileEditFragment.this, data.getData(), this);
                    break;
                case REQ_CAMERA:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if (bitmap != null) {
                        File file = RealFilePath.getRealFileFromBitmap(getContext(), bitmap);
                        if (file != null) {
                            RealFilePath.cropImage(ProfileEditFragment.this, Uri.fromFile(file), this);
                        } else {
                            //todo error save camera image
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        switch (requestCode) {
            case REQ_GALLERY:
                pickGallery();
                break;
            case REQ_CAMERA:
                pickCamera();
                break;
            case REQ_PERMISSION:
                showChangeAvatar();
                break;
        }
    }

    private void saveProfile(boolean isBackPress) {
        getPresenter().handleSaveProfile(
                edtName.getText(),
                editPhone.getText(),
                editBirthDay.getText(),
                editIdentifyId.getText(),
                editEmail.getText(),
                spCity.getSelectedPosition(),
                isBackPress);
    }

    private void showChangeAvatar() {
        ChangeAvatarDialog dialog = new ChangeAvatarDialog();
        dialog.setCallback(new ChangeAvatarDialog.Callback() {
            @Override
            public void onPickGalleryClicked() {
                pickGallery();
            }

            @Override
            public void onPickCameraClicked() {
                pickCamera();
            }
        });
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }

    private void pickGallery() {
        if (!PermissionUtil.checkAndRequestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, REQ_GALLERY)) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQ_GALLERY);
        }
    }

    private void pickCamera() {
        if (!PermissionUtil.checkAndRequestPermission(this, Manifest.permission.CAMERA, REQ_CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQ_CAMERA);
        }
    }

}
