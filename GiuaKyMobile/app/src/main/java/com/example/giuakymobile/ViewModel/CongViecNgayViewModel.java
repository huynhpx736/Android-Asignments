package com.example.giuakymobile.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.giuakymobile.api.ApiInstance;
import com.example.giuakymobile.api.apiService.CongViecNgayApiService;
import com.example.giuakymobile.model.CongViecNgay;
import com.example.giuakymobile.util.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongViecNgayViewModel extends ViewModel {
    public MutableLiveData<Resource<List<CongViecNgay>>> danhSachCongViecNgay = new MutableLiveData<>(new Resource.Unspecified<>());

    private CongViecNgayApiService service = ApiInstance.getRetrofitInstance().create(CongViecNgayApiService.class);
    public MutableLiveData<String> soViec = new MutableLiveData<>("");

    private List<CongViecNgay> congViecNgayList = new ArrayList<>();
    private int maNd;
    private String ngay;

    boolean trangThai;
    private int tinhChat;

    public void taiDanhSachCongViecNgayTheoTrangThaiVaTinhChat(int maNd, String ngay, boolean trangThai, int tinhChat) {
        this.maNd= maNd;
        this.ngay= ngay;
        this.trangThai= trangThai;
        this.tinhChat= tinhChat;
        danhSachCongViecNgay.postValue(new Resource.Loading<>());

        Call<List<CongViecNgay>> call = service.layDanhSachCongViecNgayTheoTrangThaiVaTinhChat(maNd, ngay, trangThai, tinhChat);
        call.enqueue(new Callback<List<CongViecNgay>>() {
            @Override
            public void onResponse(Call<List<CongViecNgay>> call, Response<List<CongViecNgay>> response) {
                if (response.isSuccessful()) {
                    List<CongViecNgay> dsCv = response.body();

                    danhSachCongViecNgay.postValue(new Resource.Success<>(dsCv));
                    congViecNgayList.clear();
                    congViecNgayList.addAll(dsCv);
                } else {
                    if (response.code() == 404) {
                        danhSachCongViecNgay.postValue(new Resource.Error<>("404"));
                        soViec.postValue("");

                    }

                }
            }

            @Override
            public void onFailure(Call<List<CongViecNgay>> call, Throwable t) {
                danhSachCongViecNgay.postValue(new Resource.Error<>("error"));
            }
        });

    }
    public void taiDanhSachCongViecNgayTheoTinhChat(int maNd, String ngay, int tinhChat) {
        this.maNd= maNd;
        this.ngay= ngay;
        this.tinhChat= tinhChat;
        danhSachCongViecNgay.postValue(new Resource.Loading<>());

        Call<List<CongViecNgay>> call = service.layDanhSachCongViecNgayTheoTinhChat(maNd, ngay, tinhChat);
        call.enqueue(new Callback<List<CongViecNgay>>() {
            @Override
            public void onResponse(Call<List<CongViecNgay>> call, Response<List<CongViecNgay>> response) {
                if (response.isSuccessful()) {
                    List<CongViecNgay> dsCv = response.body();

                    danhSachCongViecNgay.postValue(new Resource.Success<>(dsCv));
                    congViecNgayList.clear();
                    congViecNgayList.addAll(dsCv);
                } else {
                    if (response.code() == 404) {
                        danhSachCongViecNgay.postValue(new Resource.Error<>("404"));
                        soViec.postValue("");

                    }

                }
            }

            @Override
            public void onFailure(Call<List<CongViecNgay>> call, Throwable t) {
                danhSachCongViecNgay.postValue(new Resource.Error<>("error"));
            }
        });

    }
    public void taiDanhSachCongViecNgayTheoTrangThai(int maNd, String ngay, boolean trangThai) {
        this.maNd= maNd;
        this.ngay= ngay;
        this.trangThai= trangThai;
        danhSachCongViecNgay.postValue(new Resource.Loading<>());

        Call<List<CongViecNgay>> call = service.layDanhSachCongViecNgayTheoTrangThai(maNd, ngay,trangThai);
        call.enqueue(new Callback<List<CongViecNgay>>() {
            @Override
            public void onResponse(Call<List<CongViecNgay>> call, Response<List<CongViecNgay>> response) {
                if (response.isSuccessful()) {
                    List<CongViecNgay> dsCv = response.body();

                    danhSachCongViecNgay.postValue(new Resource.Success<>(dsCv));
                    congViecNgayList.clear();
                    congViecNgayList.addAll(dsCv);
                } else {
                    if (response.code() == 404) {
                        danhSachCongViecNgay.postValue(new Resource.Error<>("404"));
                        soViec.postValue("");

                    }

                }
            }

            @Override
            public void onFailure(Call<List<CongViecNgay>> call, Throwable t) {
                danhSachCongViecNgay.postValue(new Resource.Error<>("error"));
            }
        });

    }
    public void taiDanhSachCongViecNgay(int maNd, String ngay) {
        this.maNd= maNd;
        this.ngay= ngay;
        danhSachCongViecNgay.postValue(new Resource.Loading<>());

        Call<List<CongViecNgay>> call = service.layDanhSachCongViecNgay(maNd, ngay);
        call.enqueue(new Callback<List<CongViecNgay>>() {
            @Override
            public void onResponse(Call<List<CongViecNgay>> call, Response<List<CongViecNgay>> response) {
                if (response.isSuccessful()) {
                    List<CongViecNgay> dsCv = response.body();

                    danhSachCongViecNgay.postValue(new Resource.Success<>(dsCv));
                    congViecNgayList.clear();
                    congViecNgayList.addAll(dsCv);
                } else {
                    if (response.code() == 404) {
                        danhSachCongViecNgay.postValue(new Resource.Error<>("404"));
                        soViec.postValue("");

                    }

                }
            }

            @Override
            public void onFailure(Call<List<CongViecNgay>> call, Throwable t) {
                danhSachCongViecNgay.postValue(new Resource.Error<>("error"));
            }
        });

    }

    public void capNhatTrangThaiCongViecNgay(int maCvNgay, int maNd, String ngay) {
        Call<List<CongViecNgay>> call = service.capNhatTrangThaiCongViecNgay(maCvNgay, maNd, ngay);
        call.enqueue(new Callback<List<CongViecNgay>>() {
            @Override
            public void onResponse(Call<List<CongViecNgay>> call, Response<List<CongViecNgay>> response) {
                List<CongViecNgay> cvnList = response.body();
//                capNhatSoViecVaPhanTram(cvnList, 200);
            }


            @Override
            public void onFailure(Call<List<CongViecNgay>> call, Throwable t) {

            }
        });
    }

    public void xoaCongViecNgay(int maCvNgay, int maNd, String ngay) {
        Call<List<CongViecNgay>> call = service.xoaCongViecNgay(maCvNgay, maNd, ngay);
        call.enqueue(new Callback<List<CongViecNgay>>() {
            @Override
            public void onResponse(Call<List<CongViecNgay>> call, Response<List<CongViecNgay>> response) {
                if (response.isSuccessful()) {
                    List<CongViecNgay> cvnList = response.body();

                }
                if (response.code() == 404) {
                    List<CongViecNgay> cvnList = response.body();
                    danhSachCongViecNgay.postValue(new Resource.Error<>("404"));
//                    capNhatSoViecVaPhanTram(cvnList, 404);
                }

            }


            @Override
            public void onFailure(Call<List<CongViecNgay>> call, Throwable t) {

            }
        });
    }

    public void luuCongViecNgay(CongViecNgay congViecNgay) {
        service.luuCongViecNgay(congViecNgay).enqueue(new Callback<CongViecNgay>() {
            @Override
            public void onResponse(Call<CongViecNgay> call, Response<CongViecNgay> response) {

            }

            @Override
            public void onFailure(Call<CongViecNgay> call, Throwable t) {

            }
        });

    }



    public void sapXepCvNgay(int luaChon) {
                List<CongViecNgay> congViecNgayListTemp=new ArrayList<>();
                congViecNgayListTemp.addAll(congViecNgayList);
                if (luaChon == 1) {
                    Collections.sort(congViecNgayListTemp, new Comparator<CongViecNgay>() {
                        @Override
                        public int compare(CongViecNgay cv1, CongViecNgay cv2) {

                            if (!cv1.getTrangThai() && cv2.getTrangThai()) {
                                return -1;
                            } else if (cv1.getTrangThai() && !cv2.getTrangThai()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    });
                } else if (luaChon == 2) {
                    Collections.sort(congViecNgayListTemp, new Comparator<CongViecNgay>() {
                        @Override
                        public int compare(CongViecNgay cv1, CongViecNgay cv2) {

                            if (cv1.getTrangThai() && !cv2.getTrangThai()) {
                                return -1;
                            } else if (!cv1.getTrangThai() && cv2.getTrangThai()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    });

                } else if (luaChon == 3) {
                    Collections.sort(congViecNgayListTemp, new Comparator<CongViecNgay>() {
                        @Override
                        public int compare(CongViecNgay cv1, CongViecNgay cv2) {
                            return cv1.getCongViec().getTinhChat() - cv2.getCongViec().getTinhChat();
                        }
                    });

                } else if (luaChon == 4) {
                    Collections.sort(congViecNgayListTemp, new Comparator<CongViecNgay>() {
                        @Override
                        public int compare(CongViecNgay cv1, CongViecNgay cv2) {

                            return cv2.getCongViec().getTinhChat() - cv1.getCongViec().getTinhChat();
                        }
                    });
                }
                danhSachCongViecNgay.postValue(new Resource.Success<>(congViecNgayListTemp));
            }


    public void locVaHienThiCacCongViec() {
        List<CongViecNgay> congViecNgayListTemp = new ArrayList<>();
        congViecNgayListTemp.addAll(congViecNgayList);
        List<CongViecNgay> congViecNgayListLoc = new ArrayList<>();
        for (int i = 0; i < congViecNgayListTemp.size(); i++) {
            if (congViecNgayListTemp.get(i).getTrangThai() == trangThai) {
                congViecNgayListLoc.add(congViecNgayListTemp.get(i));
            }
        }
        danhSachCongViecNgay.postValue(new Resource.Success<>(congViecNgayListLoc));
    }
}