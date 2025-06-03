package com.mirea.iri.kt.dnschecker;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("variant")
    @Expose
    private Integer variant;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("task")
    @Expose
    private String task;
    @SerializedName("data")
    @Expose
    private String data;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public Integer getVariant() {
        return variant;
    }

    public void setVariant(Integer variant) {
        this.variant = variant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Ответ сервера:\n" +
                "Код результата: " + resultCode + "\n" +
                "Вариант: " + variant + "\n" +
                "Название приложения:\n" + title + "\n" +
                "Задание:\n" + task + "\n" + "Дополнительная информация:\n" + data;

    }


}
