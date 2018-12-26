package com.tvo.htc.view.main.support.findlocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CityResponse;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tvo.htc.R;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.util.location.GPSTracker;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FindLocationFragment extends BaseFragment<FindLocationContract.Presenter>
        implements FindLocationContract.View,
        OnMapReadyCallback, DirectionAsync.DirectionCallback, FindLocationPagerAdapter.HotLineCallBack {

    public static final String TAG = FindLocationFragment.class.getSimpleName();
    public static final int REQ_CALL = 0;

    private GoogleMap mGoogleMap;
    public Location mLocation;
    private LatLng otLocation;
    private Polyline mPolyline;
    private int mZoomPosition = 170;
    private int mType = 2;

    private List<AgenciesResponse.Agency> mList;
    private List<CityResponse.City> mListCity = null;
    private AgenciesResponse.Agency mItems;
    private CityResponse.City mItemCity;

    private FindLocationListAdapter listAdapter;
    private FindLocationPagerAdapter pagerAdapter;

    private List<Marker> mListMarker = new ArrayList<>();
    private Marker mLastMarker = null;
    private LatLng mMarkerLatLng;
    private LatLngBounds.Builder mBuilder;
    private boolean mHasMarker = false;

    private BottomSheetBehavior bottomSheetBehavior;
    private float mSlideOffset = 0;
    private int mLineHeight = 100, mScreenHeight = 0;
    private double mMinHeight = 0.3, mMaxHeight = 0.6;

    private String mHotline = null;

    @BindView(R.id.navigationBar)
    CpnNavigationBar navigationBar;
    @BindView(R.id.rvListAgency)
    RecyclerView rvListAgency;
    @BindView(R.id.btnShowList)
    ImageView btnShowList;
    @BindView(R.id.btnBackList)
    ImageView btnBackList;
    @BindView(R.id.vpListAgency)
    ViewPager vpListAgency;
    @BindView(R.id.layoutList)
    RelativeLayout layoutList;
    @BindView(R.id.layoutButton)
    LinearLayout layoutButton;
    @BindView(R.id.btnGoogleMap)
    CpnButton btnGoogleMap;
    @BindView(R.id.btnDirection)
    CpnButton btnDirection;
    @BindView(R.id.dialogSearch)
    RelativeLayout dialogSearch;
    @BindView(R.id.btnSearch)
    CpnButton btnSearch;
    @BindView(R.id.spinCity)
    CpnSpinner spinCity;
    @BindView(R.id.edtAgency)
    EditText edtAgency;
    @BindView(R.id.layoutListAgency)
    LinearLayout layoutListAgency;
    @BindView(R.id.layoutMap)
    RelativeLayout layoutMap;
    @BindView(R.id.rootView)
    CoordinatorLayout rootView;
    @BindView(R.id.btnWorkShop)
    TextView btnWorkShop;
    @BindView(R.id.btnShowRoom)
    TextView btnShowRoom;

    public static FindLocationFragment newInstance() {
        Bundle args = new Bundle();

        FindLocationFragment fragment = new FindLocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FindLocationContract.Presenter createPresenterInstance() {
        return new FindLocationPresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_location;
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_BACK:
                if (((MainActivity) getActivity()).getTabTypeSelected() == CpnTab.TabType.HOME) {
                    ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.SUPPORT);
                } else {
                    super.onNavigationBtClick(type);
                }
                break;
            case BT_SEARCH:
                if (mListCity == null) {
                    getPresenter().loadCityName();
                }
                navigationBar.setBtCloseVisibility(View.VISIBLE);
                navigationBar.setBtSearchVisibility(View.GONE);
                dialogSearch.setVisibility(View.VISIBLE);
                bottomSheetBehavior.setHideable(false);
                bottomSheetBehavior.setPeekHeight(mLineHeight);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                layoutMap.setLayoutParams(new CoordinatorLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                break;
            case BT_CLOSE:
                navigationBar.setBtCloseVisibility(View.GONE);
                navigationBar.setBtSearchVisibility(View.VISIBLE);
                dialogSearch.setVisibility(View.GONE);
                break;
            default:
                super.onNavigationBtClick(type);
                break;
        }
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels - 60;

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                this.getChildFragmentManager().findFragmentById(R.id.fragmentMap);
        supportMapFragment.getMapAsync(this);
        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mItemCity = mListCity.get(position);
                Logger.d(mItemCity.getId() + " " + mItemCity.getAreaId() + " " + mItemCity.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (mScreenHeight * mMinHeight)
        ));
        bottomSheetBehavior = BottomSheetBehavior.from(layoutListAgency);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(mLineHeight);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Logger.d(newState + "");
                if (rvListAgency.getVisibility() == View.VISIBLE) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED && mSlideOffset > 0 && layoutList.getHeight() > (int) (mScreenHeight * 0.4)) {
                        bottomSheetBehavior.setPeekHeight((int) (mScreenHeight * mMinHeight));
                        layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (mScreenHeight * mMaxHeight)
                        ));
                        return;
                    }

                    if (newState == BottomSheetBehavior.STATE_EXPANDED && mSlideOffset > 0) {
                        bottomSheetBehavior.setPeekHeight((int) (mScreenHeight * mMinHeight));
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (mScreenHeight * mMaxHeight)
                        ));
                        return;
                    }
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED && mSlideOffset == 0) {
                        bottomSheetBehavior.setPeekHeight(mLineHeight);
                        layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (mScreenHeight * mMinHeight)
                        ));
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (rvListAgency.getVisibility() == View.VISIBLE) {
                    mSlideOffset = slideOffset;
                    bottomSheetBehavior.setPeekHeight(mLineHeight);
                    if (bottomSheetBehavior.getState() == 1 && slideOffset >= mMaxHeight) {
                        layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (mScreenHeight * mMaxHeight)
                        ));
                    }
                }
                updateMap(bottomSheet);
            }
        });

    }

    private void updateMap(View bottomSheet) {
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        //int maxHeight = bottomSheet.getContext().getResources().getDisplayMetrics().heightPixels;
        int maxHeight = rootView.getHeight();
        layoutParams.setMargins(0, 0, 0, (int) (maxHeight - bottomSheet.getY()));
        layoutMap.setLayoutParams(layoutParams);
    }

    @OnClick({R.id.btnShowList,/*R.id.btnGps,*/ R.id.btnBackList, R.id.btnGoogleMap, R.id.btnDirection, R.id.dialogSearch, R.id.btnSearch, R.id.btnWorkShop, R.id.btnShowRoom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBackList:
                rvListAgency.setVisibility(View.VISIBLE);
                vpListAgency.setVisibility(View.GONE);
                btnBackList.setVisibility(View.GONE);
                layoutButton.setVisibility(View.GONE);
                bottomSheetBehavior.setPeekHeight((int) (mScreenHeight * mMinHeight));
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) (mScreenHeight * mMaxHeight)
                ));
                break;
            case R.id.btnGoogleMap:
                if (getContext() == null || mLocation == null || otLocation == null) {
                    return;
                }
                Utils.openGoogleMap(getContext(), mLocation, otLocation);
                break;
            case R.id.btnDirection:
                if (mLocation == null) {
                    return;
                }
                LatLng origin = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
                LatLng destination = new LatLng(mItems.getLatitude(), mItems.getLongitude());
                DirectionAsync async = new DirectionAsync(getActivity());
                async.execute(origin, destination);
                async.setSetListener(this);

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(origin);
                builder.include(destination);
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, mZoomPosition);
                mGoogleMap.animateCamera(cu);
                break;
            case R.id.dialogSearch:
                dialogSearch.setVisibility(View.GONE);
                navigationBar.setBtCloseVisibility(View.GONE);
                navigationBar.setBtSearchVisibility(View.VISIBLE);
                break;
            case R.id.btnSearch:
                if (mListCity != null && mLocation != null) {
                    navigationBar.setBtCloseVisibility(View.GONE);
                    navigationBar.setBtSearchVisibility(View.VISIBLE);
                    dialogSearch.setVisibility(View.GONE);
                    getPresenter().loadAgencyList(mLocation, mItemCity.getId(), edtAgency.getText().toString(), mType);
                }
                break;
            case R.id.btnWorkShop:
                changeColor(btnWorkShop, btnShowRoom);
                if (mLocation != null) {
                    mType = 2;
                    getPresenter().loadMarkerNearBy(mLocation, mType);
                }
                break;
            case R.id.btnShowRoom:
                changeColor(btnShowRoom, btnWorkShop);
                if (mLocation != null) {
                    mType = 1;
                    getPresenter().loadMarkerNearBy(mLocation, mType);
                }
                break;
//            case R.id.btnGps:
//                if (mLocation != null) {
//                    getPresenter().loadMarkerNearBy(mLocation, mType);
//                }
//                break;
        }
    }

    public void changeColor(TextView a, TextView b) {
        a.setBackgroundResource(R.drawable.ic_choose_find_location);
        b.setBackgroundResource(R.color.colorTransparent);
        a.setTextColor(getResources().getColor(R.color.colorWhite));
        b.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    @Override
    public void displayMarker(List<AgenciesResponse.Agency> items) {
        mList = items;
        otLocation = new LatLng(mList.get(0).getLatitude(), mList.get(0).getLongitude());
        mListMarker.clear();
        mLastMarker = null;
        mBuilder = new LatLngBounds.Builder();
        mGoogleMap.clear();
        mHasMarker = false;
        for (int i = 0; i < mList.size(); i++) {
            mMarkerLatLng = new LatLng(mList.get(i).getLatitude(), mList.get(i).getLongitude());
            mListMarker.add(mGoogleMap.addMarker(new MarkerOptions()
                    .position(mMarkerLatLng)
                    .icon(LibUtils.ResourceToDescriptor(R.drawable.ic_marker))));
            mBuilder.include(mMarkerLatLng);
            mHasMarker = true;
        }
        if (mHasMarker) {
            LatLngBounds bounds = mBuilder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, mZoomPosition);
            mGoogleMap.animateCamera(cu);
        }
        listAdapter = new FindLocationListAdapter(getActivity(), items, mType);
        listAdapter.setLocation(mLocation);
        pagerAdapter = new FindLocationPagerAdapter(getActivity(), items, mType, this);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvListAgency.setLayoutManager(llm);
        rvListAgency.setAdapter(listAdapter);
        vpListAgency.setAdapter(pagerAdapter);
        vpListAgency.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.page_margin));
        vpListAgency.setOffscreenPageLimit(2);
        listAdapter.notifyDataSetChanged();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, mLineHeight);
        layoutMap.setLayoutParams(layoutParams);

        listAdapter.setOnItemClickListener((adapter, view, position) -> {
            getPresenter().loadOnItemClick(mList, position);
            checkPosition(position, mListMarker.get(position));
            bottomSheetBehavior.setPeekHeight(mLineHeight);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) (mScreenHeight * mMinHeight)
            ));
            layoutMap.setLayoutParams(new CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) (mScreenHeight * 0.7) - mLineHeight
            ));
        });

        vpListAgency.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                otLocation = new LatLng(mList.get(i).getLatitude(), mList.get(i).getLongitude());
                getPresenter().loadOnPageChange(items, i);
                checkPosition(i, mListMarker.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mGoogleMap.setOnMarkerClickListener(marker -> {
            int index = mListMarker.indexOf(marker);
            if (index != -1) {
                displayOnMarkerClick(index);
            }
            bottomSheetBehavior.setPeekHeight(mLineHeight);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            layoutList.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) (mScreenHeight * mMinHeight)
            ));
            rvListAgency.setVisibility(View.GONE);
            vpListAgency.setVisibility(View.VISIBLE);
            btnBackList.setVisibility(View.VISIBLE);
            layoutButton.setVisibility(View.VISIBLE);
            return false;
        });
    }

    private void checkPosition(int position, Marker marker) {
        mItems = mList.get(position);
        listAdapter.isClick = true;
        listAdapter.backupPosition = position;
        listAdapter.notifyDataSetChanged();

        if (mLastMarker != null) {
            mLastMarker.setIcon(LibUtils.ResourceToDescriptor(R.drawable.ic_marker));
        }
        marker.setIcon(LibUtils.ResourceToDescriptor(R.drawable.ic_marker_red));
        mLastMarker = marker;
    }

    @Override
    public void displayMyLocation(Location location) {
        mLocation = location;
    }

    @Override
    public void displayNearbyLocation(Location location) {
        getPresenter().loadMarkerNearBy(location, mType);
    }

    @Override
    public void displayOnItemClick(List<AgenciesResponse.Agency> items, int position) {
        LatLng myLatLng = new LatLng(items.get(position).getLatitude(), items.get(position).getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myLatLng)
                .zoom(15)
                .build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        vpListAgency.setCurrentItem(position);
        rvListAgency.setVisibility(View.GONE);
        vpListAgency.setVisibility(View.VISIBLE);
        btnBackList.setVisibility(View.VISIBLE);
        layoutButton.setVisibility(View.VISIBLE);
        onItemHighLight(position);
    }

    @Override
    public void displayOnPageChange(List<AgenciesResponse.Agency> items, int position) {
        LatLng myLatLng = new LatLng(mList.get(vpListAgency.getCurrentItem()).getLatitude(), mList.get(vpListAgency.getCurrentItem()).getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myLatLng)
                .zoom(15)
                .build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        onItemHighLight(position);
    }

    @Override
    public void displayOnMarkerClick(int position) {
        vpListAgency.setCurrentItem(position);
        checkPosition(position, mListMarker.get(position));
        onItemHighLight(position);
    }

    @Override
    public void getCityName(List<CityResponse.City> items) {
        mListCity = new ArrayList<>();
        mListCity.add(new CityResponse.City("- Chọn tỉnh/thành phố -", 0, 0));
        mListCity.addAll(items);
        mItemCity = mListCity.get(0);
        List<String> listName = new ArrayList<>();
        for (CityResponse.City x : mListCity) {
            listName.add(x.getName());
        }
        spinCity.setData(listName);
    }

    private void onItemHighLight(int position) {
        if (layoutList.getVisibility() == View.VISIBLE) {
            rvListAgency.smoothScrollToPosition(position);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);

        if (!GPSTracker.checkAndRequestGPSPermission(this)) {
            mGoogleMap.setMyLocationEnabled(true);
        }

        mGoogleMap.setOnMyLocationButtonClickListener(() -> {
            if (mLocation != null) {
                getPresenter().loadMarkerNearBy(mLocation, mType);
            }
            return false;
        });
        //getPresenter().loadMarker();
        getPresenter().loadMyLocation(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case GPSTracker.LOCATION_REQUEST_CODE:
                boolean isGranted = true;
                if (grantResults != null && grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                            isGranted = false;
                        }
                    }
                } else {
                    isGranted = false;
                }

                if (isGranted) {
                    Toast.makeText(getActivity(), "Permission granted!", Toast.LENGTH_LONG).show();
                    getPresenter().loadMyLocation(this);
                    if (!GPSTracker.checkAndRequestGPSPermission(this)) {
                        mGoogleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            case REQ_CALL:
                if (grantResults[0] == PERMISSION_GRANTED) {
                    setHotlineCallback(mHotline);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void directionCallback(ArrayList<LatLng> latLngs) {
        Log.d(TAG, "directionCallback: " + latLngs.toString());
        if (mPolyline != null) {
            mPolyline.remove();
        }
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.BLUE);
        polylineOptions.geodesic(true);
        polylineOptions.addAll(latLngs);
        mPolyline = mGoogleMap.addPolyline(polylineOptions);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getPresenter().stopRequestLocation();
    }

    @Override
    public void setHotlineCallback(String hotline) {
        if (hotline == null || hotline.isEmpty()) {
            return;
        }
        mHotline = hotline;
        if (!PermissionUtil.checkAndRequestPermission(this, Manifest.permission.CALL_PHONE, REQ_CALL)) {
            Utils.callPhone(getContext(), hotline);
        }
    }
}
