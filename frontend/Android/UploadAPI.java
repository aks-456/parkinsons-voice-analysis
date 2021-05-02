package com.pinnaclesoftwaresolutions.ews;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadAPI {
    @Multipart
    @POST(" ")
    Call<RequestBody> uploadFile(@Part MultipartBody.Part part, @Part("file") RequestBody requestBody);
}
