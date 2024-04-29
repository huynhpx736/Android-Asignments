package com.example.giuakymobile.fragment;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.giuakymobile.R;
//
//public class CongViecFragment extends Fragment {
//
//    }
//
//
//    public static CongViecFragment newInstance(String param1, String param2) {
//        CongViecFragment fragment = new CongViecFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cong_viec, container, false);
//    }
//}


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.giuakymobile.ViewModel.CongViecNgayViewModel;
import com.example.giuakymobile.adapter.DanhSachCongViecNgayAdapter;
import com.example.giuakymobile.databinding.FragmentCongViecBinding;
import com.example.giuakymobile.model.CongViecNgay;
import com.example.giuakymobile.util.Resource;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;

public class CongViecFragment extends Fragment {
    int maNd = 2;
    private CongViecNgayViewModel congViecNgayViewModel;
    private DanhSachCongViecNgayAdapter danhSachCongViecNgayAdapter;

    private FragmentCongViecBinding binding;

    private Calendar calendar = Calendar.getInstance();
    private int nam = calendar.get(Calendar.YEAR);
    private int thang = calendar.get(Calendar.MONTH); // Tháng bắt đầu từ 0
    private int ngay = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCongViecBinding.inflate(getLayoutInflater());
//        ShowHideMenu showHideMenu = new ShowHideMenu();
//        showHideMenu.showBottomNavigation(this);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        congViecNgayViewModel = new CongViecNgayViewModel();
        khaiBaoAdapter();
        binding.rvCongViec.setAdapter(danhSachCongViecNgayAdapter);
        binding.rvCongViec.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        taiDanhSachCongViecNgay(maNd, dinhDangNgayAPI(ngay, thang, nam));

        binding.tvNgay.setText(dinhDangNgay(ngay, thang, nam));
        binding.btnLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLichDialog();
            }
        });

        binding.btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLocButtonClick();
            }
        });
        khaiBaoSpinner();

    }
    public void onLocButtonClick( ) {

//         Lấy giá trị đã chọn từ Spinner spLocTrangThai và spLocTinhChat
        int locTrangThai = binding.spLocTrangThai.getSelectedItemPosition();
        int locTinhChat = binding.spLocTinhChat.getSelectedItemPosition();
        switch (locTrangThai) {
            case 0: {
                if (locTinhChat == 0)
                    congViecNgayViewModel.taiDanhSachCongViecNgay(maNd, dinhDangNgayAPI(ngay, thang, nam));
                else
                    congViecNgayViewModel.taiDanhSachCongViecNgayTheoTinhChat(maNd, dinhDangNgayAPI(ngay, thang, nam), (locTinhChat - 1));
                break;
            }
            case 1: {
                if (locTinhChat == 0)
                    congViecNgayViewModel.taiDanhSachCongViecNgayTheoTrangThai(maNd, dinhDangNgayAPI(ngay, thang, nam), true);
                else
                    congViecNgayViewModel.taiDanhSachCongViecNgayTheoTrangThaiVaTinhChat(maNd, dinhDangNgayAPI(ngay, thang, nam), true, (locTinhChat - 1));
                break;
            }
            case 2: {
                if (locTinhChat == 0)
                    congViecNgayViewModel.taiDanhSachCongViecNgayTheoTrangThai(maNd, dinhDangNgayAPI(ngay, thang, nam), false);
                else
                    congViecNgayViewModel.taiDanhSachCongViecNgayTheoTrangThaiVaTinhChat(maNd, dinhDangNgayAPI(ngay, thang, nam), false, (locTinhChat - 1));
                break;
            }


        }
    }

    private void khaiBaoSpinner() {
        String[] luaChon = {"Tất cả", "Đã hoàn thành", "Chưa hoàn thành"};
        String[] tinhChat = {"Tất cả", "Bình thường", "Quan trọng", "Rất quan trọng"};
        ArrayAdapter<String> adapterTrangThai = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, luaChon);
        ArrayAdapter<String> adapterTinhChat = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, tinhChat);
        binding.spLocTrangThai.setAdapter(adapterTrangThai);
        binding.spLocTinhChat.setAdapter(adapterTinhChat);
        onLocButtonClick();

    }

    private void taiDanhSachCongViecNgay(int maNd, String ngay) {
        congViecNgayViewModel.taiDanhSachCongViecNgay(maNd, ngay);
        congViecNgayViewModel.danhSachCongViecNgay.observe(getViewLifecycleOwner(), new Observer<Resource<List<CongViecNgay>>>() {
            @Override
            public void onChanged(Resource<List<CongViecNgay>> list) {
                if (list instanceof Resource.Loading) {
//                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.btnLoc.setVisibility(View.GONE);
                    binding.spLocTrangThai.setVisibility(View.GONE);
                    binding.spLocTinhChat.setVisibility(View.GONE);
                }
                if (list instanceof Resource.Success) {
//                    binding.progressBar.setVisibility(View.GONE);
                    binding.tvTrangThai.setVisibility(View.VISIBLE);
                    binding.tvTinhChat.setVisibility(View.VISIBLE);
                    binding.btnLoc.setVisibility(View.VISIBLE);
                    binding.spLocTrangThai.setVisibility(View.VISIBLE);
                    binding.spLocTinhChat.setVisibility(View.VISIBLE);
                    danhSachCongViecNgayAdapter.differ.submitList(list.getData());

                }
                if (list instanceof Resource.Error) {
                    System.out.println("error");
//                    binding.progressBar.setVisibility(View.GONE);
                    binding.spLocTrangThai.setVisibility(View.GONE);
                    binding.spLocTinhChat.setVisibility(View.GONE);
                    if (list.getMessage().equals("error")) {
                        Toast.makeText(requireContext(), "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_LONG).show();
                    }
                    if (list.getMessage().equals("404")) {
                        danhSachCongViecNgayAdapter.differ.submitList((List<CongViecNgay>) new Resource.Unspecified<>().getData());
//                        binding.progressBar.setVisibility(View.GONE);


                    }

                }
            }
        });
    }

    private void khaiBaoAdapter() {
        danhSachCongViecNgayAdapter = new DanhSachCongViecNgayAdapter();
        danhSachCongViecNgayAdapter.setOnItemClickListener(new DanhSachCongViecNgayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CongViecNgay congViecNgay) {

            }

            @Override
            public void onCheckBoxClick(int maCongViecNgay) {
                congViecNgayViewModel.capNhatTrangThaiCongViecNgay(maCongViecNgay, maNd, dinhDangNgayAPI(ngay, thang, nam));


            }

            @Override
            public void onImageButtonClick(CongViecNgay congViecNgay) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setMessage("Bạn chắc chắn xoá ?");
                builder.setTitle("Xác nhận !");
                builder.setCancelable(false);
                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Thực hiện xóa công việc ở đây
                        int maCvNgay = congViecNgay.getMaCvNgay();
                        // Gọi phương thức xóa công việc từ ViewModel
                        congViecNgayViewModel.xoaCongViecNgay(maCvNgay, maNd, dinhDangNgayAPI(ngay, thang, nam));
                        // Hiển thị Snackbar thông báo đã xóa công việc
                        Snackbar.make(getView(), "Đã xoá công việc", Snackbar.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nếu người dùng chọn Huỷ, không thực hiện xóa và đóng dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }


        });

//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(
//                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
//        ) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return true;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
//                builder.setMessage("Bạn chắc chắn xoá ?");
//                builder.setTitle("Xác nhận !");
//                builder.setCancelable(false);
//                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int position = viewHolder.getAdapterPosition();
//                        CongViecNgay congViecNgay = danhSachCongViecNgayAdapter.getDiffer().getCurrentList().get(position);
//                        congViecNgayViewModel.xoaCongViecNgay(congViecNgay.getMaCvNgay(), maNd, dinhDangNgayAPI(ngay, thang, nam));
//
//                        Snackbar.make(getView(), "Đã xoá công việc", Snackbar.LENGTH_LONG).show();
//                    }
//                });
//
//                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        danhSachCongViecNgayAdapter.notifyDataSetChanged();
//
//                        dialog.cancel();
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//
//
//            }
//        };

//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvCongViec);
    }

    private void openLichDialog() {

        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                binding.tvNgay.setText(dinhDangNgay(day, month, year));
                taiDanhSachCongViecNgay(maNd, dinhDangNgayAPI(day, month, year));
                ngay = day;
                thang = month;
                nam = year;
            }
        }, nam, thang, ngay);
        dialog.show();
    }

    private String dinhDangNgay(int ngay, int thang, int nam) {

        String temp = "";
        if (ngay < 10)
            temp += "0" + String.valueOf(ngay);
        else temp += String.valueOf(ngay);
        temp += "/";
        if (thang + 1 < 10)
            temp += "0" + String.valueOf(thang + 1);
        else temp += String.valueOf(thang + 1);
        temp += "/";
        temp += nam;
        return temp;
    }


    private String dinhDangNgayAPI(int ngay, int thang, int nam) {

        String temp = "";
        temp += nam;
        temp += "-";

        if (thang + 1 < 10)
            temp += "0" + String.valueOf(thang + 1);
        else temp += String.valueOf(thang + 1);
        temp += "-";
        if (ngay < 10)
            temp += "0" + String.valueOf(ngay);
        else temp += String.valueOf(ngay);

        return temp;
    }
}