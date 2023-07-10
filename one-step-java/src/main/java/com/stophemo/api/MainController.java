package com.stophemo.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.stophemo.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/hello")
    @ApiOperation(value = "Say hello", notes = "Returns a greeting message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = String.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public JSONArray getArea() {
        JSONArray areaArray = null;
        String url = "https://game.qq.com/comm-htdocs/js/game_area/dnf_server_select.js";
        byte[] data = HttpUtil.doDownload(url, null, null, null);
        try {
            String result = new String(data, "GBK");
            String json = StrUtil.subBetween(result, "DNFServerSelect.STD_DATA= ", ";");
            if (json != null) {
                areaArray = JSONUtil.parseArray(json);
            }
        } catch (Exception e) {
            log.error("msg", e);
        }
        return areaArray;
    }
}
