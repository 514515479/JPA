package jpa.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: tobi
 * @Date: 2020/4/5 14:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private static final Integer SUCCESS = 200;
    private static final Integer FAILED = 500;

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, null, data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(FAILED, null, null);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<>(FAILED, message, null);
    }

    @JsonIgnore
    public Boolean isSuccess() {
        return this.code.equals(SUCCESS);
    }
}

