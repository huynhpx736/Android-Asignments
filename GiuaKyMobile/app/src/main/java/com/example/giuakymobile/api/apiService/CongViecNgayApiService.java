package com.example.giuakymobile.api.apiService;

import com.example.giuakymobile.model.CongViecNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CongViecNgayApiService {
    @GET("CongViecNgay/NguoiDung/{maNd}/{ngay}/{trangThai}/{tinhChat}")
    Call<List<CongViecNgay>> layDanhSachCongViecNgayTheoTrangThaiVaTinhChat(@Path("maNd") int maNd, @Path("ngay") String ngay,@Path("trangThai") boolean trangThai,@Path("tinhChat") int tinhChat);

    @GET("CongViecNgay/NguoiDung/{maNd}/{ngay}/tinhChat/{tinhChat}")
    Call<List<CongViecNgay>> layDanhSachCongViecNgayTheoTinhChat(@Path("maNd") int maNd, @Path("ngay") String ngay,@Path("tinhChat") int tinhChat);

    @GET("CongViecNgay/NguoiDung/{maNd}/{ngay}/trangThai/{trangThai}")
    Call<List<CongViecNgay>> layDanhSachCongViecNgayTheoTrangThai(@Path("maNd") int maNd, @Path("ngay") String ngay,@Path("trangThai") boolean trangThai);

    @GET("CongViecNgay/NguoiDung/{maNd}/{ngay}")
    Call<List<CongViecNgay>> layDanhSachCongViecNgay(@Path("maNd") int maNd, @Path("ngay") String ngay);
    @GET("CongViecNgay/{maCvNgay}")
    Call<CongViecNgay> layCongViecNgay(@Path("maCvNgay") int maCvNgay);

    @PUT("CongViecNgay/CapNhatTrangThai/{maCvNgay}/{maNd}/{ngay}")
    Call<List<CongViecNgay>> capNhatTrangThaiCongViecNgay(@Path("maCvNgay") int maCvNgay, @Path("maNd") int maNd, @Path("ngay") String ngay);

    @DELETE("CongViecNgay/XoaCongViecNgay/{maCvNgay}/{maNd}/{ngay}")
    Call<List<CongViecNgay>> xoaCongViecNgay(@Path("maCvNgay") int maCvNgay, @Path("maNd") int maNd, @Path("ngay") String ngay);

    @POST("CongViecNgay/LuuCongViecNgay")
    Call<CongViecNgay> luuCongViecNgay(@Body CongViecNgay congViecNgay);
}
