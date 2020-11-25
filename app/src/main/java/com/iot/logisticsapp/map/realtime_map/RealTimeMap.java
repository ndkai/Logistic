package com.iot.logisticsapp.map.realtime_map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.Model.Kho;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;
import com.iot.logisticsapp.R;
import com.iot.logisticsapp.utils.Constants;
import com.iot.logisticsapp.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class RealTimeMap extends AppCompatActivity implements OnMapReadyCallback {


    private List<CungCapHangHoa> cungCapHangHoaList;
    private List<CungCapVanTai> cungCapVanTaiList;
    private List<Kho> khoList;
    private List<NguoiNhanCuuTro> nguoiNhanCuuTroList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cungCapHangHoaRef = db.collection("CungCapHangHoa");
    private CollectionReference cungCapVanTaiRef = db.collection("CungCapVanTai");
    private CollectionReference khoRef = db.collection("Kho");
    private CollectionReference nguoiNhanCuuTroRef = db.collection("NguoiNhanCuuTro");
    private GoogleMap map;
    private Bitmap goodBitmap;
    private Bitmap good2Bitmap;
    private Bitmap personBitmap;
    private Bitmap person2Bitmap;
    private Bitmap truckBitmap;
    private Bitmap truck2Bitmap;
    private Bitmap wareHouseBitmap;
    private Bitmap wareHouse2Bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_map);
        cungCapHangHoaList = new ArrayList<>();
        cungCapVanTaiList = new ArrayList<>();
        khoList = new ArrayList<>();
        nguoiNhanCuuTroList = new ArrayList<>();

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng cs3 = new LatLng(10.8657771, 106.6185532);
        map = googleMap;

        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(cs3, Constants.DEFAULT_ZOOM));

    }

    @Override
    protected void onStart() {
        super.onStart();
        initMarkerIcons();
        fetchData();
    }

    private void addMarkers() {
        map.clear();
        for (CungCapHangHoa i :
                cungCapHangHoaList) {
            if (i.getTenLoaiItem().equals(Constants.THONG_THUONG)) {
                addMarker(good2Bitmap, i.getLocation());
            } else {
                addMarker(goodBitmap, i.getLocation());
            }
        }
        for (CungCapVanTai i : cungCapVanTaiList) {
            if (i.getLoaiXe().equals(Constants.THONG_THUONG)) {
                addMarker(truck2Bitmap, i.getLocation());

            } else {
                addMarker(truckBitmap, i.getLocation());
            }
        }
        for (Kho i : khoList) {
            if (i.getLoai().equals(Constants.THONG_THUONG)) {
                addMarker(wareHouse2Bitmap, i.getLocation());
            } else {
                addMarker(wareHouseBitmap, i.getLocation());
            }
        }
        for (NguoiNhanCuuTro i : nguoiNhanCuuTroList) {
            if (i.getLoai().equals(Constants.THONG_THUONG)) {
                addMarker(personBitmap, i.getLocation());
            } else {
                addMarker(person2Bitmap, i.getLocation());
            }
        }
    }

    private void initMarkerIcons() {
        goodBitmap = Helper.convertIconRawToMaker(getResources(), R.raw.goods, "Hàng hóa lạnh");
        good2Bitmap = Helper.convertIconRawToMaker(getResources(), R.raw.goods2, "Hàng hóa");
        personBitmap = Helper.convertIconRawToMaker(getResources(), R.raw.person, "Người YC 2");
        person2Bitmap = Helper.convertIconRawToMaker(getResources(), R.raw.person2, "Người YC");
        wareHouseBitmap = Helper.convertIconRawToMaker(getResources(), R.raw.warehouse, "Kho lạnh");
        wareHouse2Bitmap = Helper.convertIconRawToMaker(getResources(), R.raw.warehouse2, "Kho");
        truckBitmap = Helper.convertIconRawToMaker(getResources(), R.raw.truck, "Xe lạnh");
        truck2Bitmap = Helper.convertIconRawToMaker(getResources(), R.raw.truck2, "Xe");
    }

    private void addMarker(Bitmap bitmap, LatLng location) {
        map.addMarker(new MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
//                 Specifies the anchor to be at a particular point in the marker image.
                .anchor(0.5f, 1));
    }

    private void fetchData() {
        cungCapHangHoaRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    cungCapHangHoaList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        CungCapHangHoa cungCapHangHoa = documentSnapshot.toObject(CungCapHangHoa.class);
                        cungCapHangHoaList.add(cungCapHangHoa);
                    }
                    addMarkers();
                }
            }
        });
        cungCapVanTaiRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    cungCapVanTaiList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        CungCapVanTai cungCapVanTai = documentSnapshot.toObject(CungCapVanTai.class);
                        cungCapVanTaiList.add(cungCapVanTai);
                    }
                    addMarkers();
                }
            }
        });
        khoRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    khoList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        Kho kho = documentSnapshot.toObject(Kho.class);
                        khoList.add(kho);
                    }
                    addMarkers();
                }
            }
        });
        nguoiNhanCuuTroRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    nguoiNhanCuuTroList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        NguoiNhanCuuTro nguoiNhanCuuTro = documentSnapshot.toObject(NguoiNhanCuuTro.class);
                        nguoiNhanCuuTroList.add(nguoiNhanCuuTro);
                    }
                    addMarkers();
                }
            }
        });
    }
}