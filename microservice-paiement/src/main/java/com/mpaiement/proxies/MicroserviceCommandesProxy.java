package com.mpaiement.proxies;

import com.mpaiement.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name="microservice-commandes", url = "localhost:9002")
public interface MicroserviceCommandesProxy {
    @PostMapping(value = "/commandes")
    public ResponseEntity<CommandeBean> ajouterCommande(@RequestBody CommandeBean commande);
    @GetMapping(value = "/commandes/{id}")
    CommandeBean recupererUneCommande(@PathVariable("id") int id);

    @PutMapping(value = "/commandes")
    void updateCommande(@RequestBody CommandeBean commande);


}
