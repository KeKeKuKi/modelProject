package per.zzz.cloud.qiniu.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author TripleZ
 */
@Builder
@Data
public class QiNiuCommonResult {
    private Boolean success;

    private String message;

    private String resultUrl;

    public static QiNiuCommonResult ofSuccess(String resultUrl){
        return QiNiuCommonResult.builder().resultUrl(resultUrl).message("success").success(true).build();
    }

    public static QiNiuCommonResult ofFail(String message){
        return QiNiuCommonResult.builder().resultUrl(null).message(message).success(false).build();
    }
}
