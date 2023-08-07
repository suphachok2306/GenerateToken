package pcc.test.token.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pcc.test.token.model.ApiModel;
import pcc.test.token.service.ApiService;
import pcc.test.token.untils.DatabaseConnectionException;

@RestController
public class ApiController {
    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/api/save")
    public ResponseEntity<ApiResponse> saveApiData(@RequestBody ApiModel apiModel) {
        ApiResponse response = new ApiResponse();

        // Check if required data is present and not null
        if (apiModel.getSsotype() == null ||
                apiModel.getSystemid() == null ||
                apiModel.getSystemname() == null ||
                apiModel.getSystemtransactions() == null ||
                apiModel.getSystemprivileges() == null ||
                apiModel.getSystemusergroup() == null ||
                apiModel.getSystemlocationgroup() == null ||
                apiModel.getUserid() == null ||
                apiModel.getUserfullname() == null ||  // Add additional checks as needed
                apiModel.getUserrdofficecode() == null ||
                apiModel.getUserofficecode() == null ||
                apiModel.getClientlocation() == null ||
                apiModel.getLocationmachinenumber() == null ||
                apiModel.getTokenid() == null){
            response.setResponseCode("E40001");
            response.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ กรอกข้อมูลไม่ครบ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String result = apiService.saveApiData(apiModel);
            response.setResponseCode("I07000");
            response.setResponseMessage("ทำรายการเรียบร้อย");

            ApiResponse.ResponseData responseData = new ApiResponse.ResponseData();
            responseData.setUserId(apiModel.getUserid());
            responseData.setTokenId(apiModel.getTokenid());

            response.setResponseData(responseData);

            return ResponseEntity.ok(response);
        } catch (DatabaseConnectionException e) {
            response.setResponseCode("E50001");
            response.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ เกิดข้อผิดพลาดในการเชื่อมต่อฐานข้อมูล");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            response.setResponseCode("E50002");
            response.setResponseMessage("!!!!!!Internal server error!!!!!!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Inner class for ApiResponse.ResponseData
    public static class ApiResponse {
        private String responseCode;
        private String responseMessage;
        private ResponseData responseData;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public ResponseData getResponseData() {
            return responseData;
        }

        public void setResponseData(ResponseData responseData) {
            this.responseData = responseData;
        }

        public static class ResponseData {
            private String userId;
            private String tokenId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getTokenId() {
                return tokenId;
            }

            public void setTokenId(String tokenId) {
                this.tokenId = tokenId;
            }
        }
    }
}
