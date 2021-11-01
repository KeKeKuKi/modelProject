package per.zzz.model.tencentOss.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author TripleZ
 */
@Builder
@Data
public class TencentOssCommonResult {
    private Boolean success;

    private String message;

    private String resultUrl;

    public static TencentOssCommonResult ofSuccess(String resultUrl){
        return TencentOssCommonResult.builder().resultUrl(resultUrl).message("success").success(true).build();
    }

    public static TencentOssCommonResult ofFail(String message){
        return TencentOssCommonResult.builder().resultUrl(null).message(message).success(false).build();
    }
}
