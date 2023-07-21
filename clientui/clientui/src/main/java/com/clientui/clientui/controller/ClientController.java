package com.clientui.clientui.controller;

import com.clientui.clientui.beans.CommandeBean;
import com.clientui.clientui.beans.PaiementBean;
import com.clientui.clientui.beans.ProductBean;
import com.clientui.clientui.proxies.MicroserviceCommandesProxy;
import com.clientui.clientui.proxies.MicroservicePaiementsProxy;
import com.clientui.clientui.proxies.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class ClientController {
    @Autowired
    private MicroserviceProduitsProxy produitsProxy;
    @Autowired
    private MicroserviceCommandesProxy commandesProxy;
    @Autowired
    private MicroservicePaiementsProxy paiementsProxy;
    @RequestMapping("/")
    public String accueil(Model model)
    {
        List<ProductBean> produits = produitsProxy.listeDesProduits();
        model.addAttribute("produits", produits);
        return "Accueil";

    }

    @GetMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model){
        ProductBean produit = produitsProxy.recupererUnProduit(id);
        model.addAttribute("produit", produit);
        return "FicheProduit";

    }

   @RequestMapping(value = "/commander-produit/{idProduit}/{montant}")
    public String passerCommande(@PathVariable int idProduit,@PathVariable double montant, Model model){

        CommandeBean commande = new CommandeBean();

        commande.setProductId(idProduit);
        commande.setQuantite(1);
        commande.setDateCommande(new Date());
        commande.setCommandePayee(false);

        ResponseEntity<CommandeBean> ResponseCommandeAjoutee = commandesProxy.ajouterCommande(commande);
        CommandeBean commandeAjoutee = ResponseCommandeAjoutee.getBody();
        model.addAttribute("commande",commandeAjoutee);
        model.addAttribute("montant",montant);
        return "Paiement";
   }

   @RequestMapping(value = "/payer-commande/{idCommande}/{montant}")
    public String payerCommande(@PathVariable int idCommande,@PathVariable double montant, Model model) {

       PaiementBean paiement = new PaiementBean();

       paiement.setIdCommande(idCommande);
       paiement.setMontant(montant);
       paiement.setNumeroCarte(numcarte());

       ResponseEntity<PaiementBean> responsePaiementRegle = paiementsProxy.payerUneCommande(paiement);

       Boolean paiementAccepte = false;
       if (responsePaiementRegle.getStatusCode() == HttpStatus.CREATED)
           paiementAccepte = true;

       model.addAttribute("paiementOk",paiementAccepte);
       return "Confirmation";
   }

   private Long numcarte(){
        return ThreadLocalRandom.current().nextLong(1000000000000000L,9000000000000000L);
   }
}
