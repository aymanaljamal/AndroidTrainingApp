package com.example.echoandlistapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface Api {
    @GET("get_messages.php")
    Call<List<Message>> getMessages(@Query("chat_id") long chatId);

    @FormUrlEncoded
    @POST("send_message.php")
    Call<ApiResponse> sendMessage(
            @Field("chat_id") long chatId,
            @Field("sender_id") long senderId,
            @Field("body") String body
    );
    @GET("get_links.php")
    Call<List<LinkItem>> getLinks(@Query("topic") String topic); // مرّر null أو "" لكل الروابط

    @GET("get_topics.php")
    Call<List<String>> getTopics();

    @FormUrlEncoded
    @POST("add_link.php")
    Call<ApiResponse> addLink(
            @Field("title") String title,
            @Field("url") String url,
            @Field("topic") String topic
    );
}
