package pcc.test.token.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcc.test.token.entity.ApiEntity;
import pcc.test.token.model.ApiModel;
import pcc.test.token.repository.ApiJpaRepository;
import java.text.ParseException;
import java.util.Date;

@Service
public class ApiService {
    private final ApiJpaRepository apiJpaRepository;

    @Autowired
    public ApiService(ApiJpaRepository apiJpaRepository) {
        this.apiJpaRepository = apiJpaRepository;
    }

    public String saveApiData(ApiModel apiModel){
        ApiEntity apiEntity = new ApiEntity();
        apiEntity.setRequest_date(new Date());
        apiEntity.setSsotype(apiModel.getSsotype());
        apiEntity.setSystemid(apiModel.getSystemid());
        apiEntity.setSystemname(apiModel.getSystemname());
        apiEntity.setSystemtransactions(apiModel.getSystemtransactions());
        apiEntity.setSystemprivileges(apiModel.getSystemprivileges());
        apiEntity.setSystemusergroup(apiModel.getSystemusergroup());
        apiEntity.setSystemlocationgroup(apiModel.getSystemlocationgroup());
        apiEntity.setUserid(apiModel.getUserid());
        apiEntity.setUserfullname(apiModel.getUserfullname());
        apiEntity.setUserrdofficecode(apiModel.getUserrdofficecode());
        apiEntity.setUserofficecode(apiModel.getUserofficecode());
        apiEntity.setClientlocation(apiModel.getClientlocation());
        apiEntity.setLocationmachinenumber(apiModel.getLocationmachinenumber());
        apiEntity.setTokenid(apiModel.getTokenid());
        apiJpaRepository.save(apiEntity);
        return "SUCCESS";
    }

}


