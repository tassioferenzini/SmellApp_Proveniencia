/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.application01;

import jade.wrapper.StaleProxyException;

/**
 *
 * @author nathyles
 */
public class MasAppPublisher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        AlertAgent client1 = new AlertAgent(1);
        


        try {
            InitAgent.init(client1, "alert1", "ALERT");

        } catch (StaleProxyException ex) {

        }

    }

}
