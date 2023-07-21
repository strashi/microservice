package com.clientui.clientui.proxies;

import com.clientui.clientui.beans.PaiementBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "microservice-paiement", url = "localhost:9003")
public interface MicroservicePaiementsProxy {

    @RequestMapping(value = "/paiement")
    public ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);
}
