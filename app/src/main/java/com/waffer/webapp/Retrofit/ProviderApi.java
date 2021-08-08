package com.waffer.webapp.Retrofit;

import com.waffer.webapp.Model.ApiResponse;
import com.waffer.webapp.Model.Appointment;
import com.waffer.webapp.Model.AppointmentSlot;
import com.waffer.webapp.Model.BookingRequest;
import com.waffer.webapp.Model.BookingResponse;
import com.waffer.webapp.Model.ConfirmAppointment;
import com.waffer.webapp.Model.Countries;
import com.waffer.webapp.Model.ImageUpload.ImageUploadResponse;
import com.waffer.webapp.Model.JobItem;
import com.waffer.webapp.Model.Login.User;
import com.waffer.webapp.Model.LoginData;
import com.waffer.webapp.Model.ManageAppointmentRequest;
import com.waffer.webapp.Model.ManagePrivacyRequest;
import com.waffer.webapp.Model.ManageServicesRequestParam;
import com.waffer.webapp.Model.Provider.BusinessHours;
import com.waffer.webapp.Model.Provider.MarkFavoriteParam;
import com.waffer.webapp.Model.Provider.PrivacySettings;
import com.waffer.webapp.Model.Provider.ProfileServices;
import com.waffer.webapp.Model.Provider.ProviderModel;
import com.waffer.webapp.Model.Provider.ProviderReviewListData;
import com.waffer.webapp.Model.RegisterBusiness;
import com.waffer.webapp.Model.RequestSlots;
import com.waffer.webapp.Model.ResetPassword;
import com.waffer.webapp.Model.ReviewProvider;
import com.waffer.webapp.Model.TokenRequestParam;
import com.waffer.webapp.Model.categories.Category;
import com.waffer.webapp.Model.order_model.GetAllPaymentMethod;
import com.waffer.webapp.Model.order_model.PlaceOrderResponse;
import com.waffer.webapp.Model.order_model.CreateOrder;
import com.waffer.webapp.Model.packages.PackageItem;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 */

public interface ProviderApi {

    String BASE_SITE = "https://waffer-sa.com/app/";

        String BASE_URL = BASE_SITE + "wp-json/api/v1/";

        String WOCOMMERCE_URL = BASE_SITE + "wp-json/wc/v3/";

        @GET("categories")
        Call<List<Category>> loadCategories();

        @GET("configs/countries")
        Call<List<Countries>> loadCountries();

        @GET("providers")
        Call<List<ProviderModel>> searchProvider(@Header("accept") String type,
                                                 @Query("user_id") int userid,
                                                 @Query("category_id") int categoryId,
                                                 @Query("keyword") String keyword,
                                                 @Query("geo") String geo,
                                                 @Query("geo_distance") String geo_distance,
                                                 @Query("country") String country,
                                                 @Query("city") String city,
                                                 @Query("zip") String zip,
                                                 @Query("lat") String lat,
                                                 @Query("long") String longitude);

    @GET("providers")
    Call<List<ProviderModel>> searchProvider(@Query("page") int page,
                                             @Query("showposts") int post,
                                             @Header("accept") String type,
                                             @Query("user_id") int userid,
                                             @Query("category_id") int categoryId,
                                             @Query("sub_categories") List<String> subCat,
                                             @Query("keyword") String keyword,
                                             @Query("geo") String geo,
                                             @Query("geo_distance") String geo_distance,
                                             @Query("country") String country,
                                             @Query("city") String city,
                                             @Query("zip") String zip,
                                             @Query("lat") String lat,
                                             @Query("long") String longitude);

        @GET("providers")
        Call<List<ProviderModel>> searchProvider(@Query("page") int page,
                                                 @Query("showposts") int post,
                                                 @Header("accept") String type,
                                                 @Query("user_id") int userid,
                                                 @Query("keyword") String keyword,
                                                 @Query("geo") String geo,
                                                 @Query("geo_distance") String geo_distance,
                                                 @Query("country") String country,
                                                 @Query("city") String city,
                                                 @Query("zip") String zip,
                                                 @Query("lat") String lat,
                                                 @Query("long") String longitude);
        @GET("providers")
        Call<List<ProviderModel>> getFeaturedProviders(@Query("page") int page,
                                                       @Query("showposts") int post,@Query("user_id") int userid, @Query("featured") String featured);

        @POST("providers")
        Call<User> registerUser(@Body RegisterBusiness post);

        @POST("user/login")
        Call<User> loginUser(@Body LoginData post);

        @GET("user/profile/{user_id}")
        Call<ProviderModel> getUserProfile(@Path("user_id") int userid);

        @POST("user/profile/{user_id}")
        Call<ApiResponse> updateUserProfile(@Path("user_id") int userid,@Body ProviderModel post);

        @Multipart
        @POST("wp-json/wp/v2/media")
        Call<ImageUploadResponse> upload(
                @Part MultipartBody.Part file
        );

        @POST("providers/appointment")
        Call<BookingResponse> makeAppointment(@Body BookingRequest post);

        @POST("providers/confirm-appointment")
        Call<ApiResponse> confirmAppointment(@Body ConfirmAppointment post);

        @GET("user/appointments")
        Call<List<Appointment>> getUserAppointments(@Query("user_id") long userid);

        @POST("user/reset-password")
        Call<ApiResponse> recoverPassword(@Body ResetPassword post);

        @POST("providers/appointment/slots")
        Call<List<AppointmentSlot>> getSlotsOfDate(@Body RequestSlots post);

        @POST("providers/save-rating")
        Call<ApiResponse> saveProviderRating(@Body ReviewProvider rating);

        @POST("user/token")
        Call<ApiResponse> saveUserToken(@Body TokenRequestParam rating);

        @GET("user/services")
        Call<List<ProfileServices>> getUserServices(@Query("user_id") long userId);

        @POST("user/services")
        Call<ApiResponse> updateUserServices(@Body ManageServicesRequestParam params);

        @POST("providers/reviews")
        Call<List<ProviderReviewListData>> getProviderReviews(@Body ReviewProvider params);

        @GET("user/favorites")
        Call<List<ProviderModel>> getUserFavorites(@Query("user_id") long userId);

        @POST("user/favorites")
        Call<ApiResponse> updateUserFavorites(@Body MarkFavoriteParam params);

        @GET("providers/manage-appointments")
        Call<List<Appointment>> getProviderAppointments(@Query("provider_id") long providerId);

        @GET("providers/manage-appointments")
        Call<List<Appointment>> getProviderAppointmentsByStatus(@Query("provider_id") long providerId,@Query("appointment_status") String status);

        @POST("providers/appointments-status")
        Call<ApiResponse> updateProviderAppointments(@Body ManageAppointmentRequest params);

        @GET("providers/privacy-settings")
        Call<PrivacySettings> getPrivacySettings(@Query("publisher_id") long providerId);

        @POST("providers/privacy-settings")
        Call<ApiResponse> updatePrivacySettings(@Body ManagePrivacyRequest request);

        @GET("jobs")
        Call<List<JobItem>> getAllJobs();

        @GET("providers/business-hours")
        Call<BusinessHours> getBusinessHour(@Query("publisher_id") long providerId);

        @POST("providers/business-hours")
        Call<ApiResponse> updateBusinessHour(@Body ManagePrivacyRequest request);

        @GET("products")
        Call<List<PackageItem>> getAllPackages();

        @GET("payment_gateways")
        Call<List<GetAllPaymentMethod>> getAllPaymentMethods();

        @POST("listing-checkout")
        Call<PlaceOrderResponse> placeOrder(@Body CreateOrder orderdata);


}
