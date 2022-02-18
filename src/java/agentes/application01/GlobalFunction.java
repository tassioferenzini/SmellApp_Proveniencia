/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.application01;

/**
 *
 * @author nathalianascimento
 */
public interface GlobalFunction {
    
            /*Esse agente determina a funcao que faz a relacao entre gases coletados 
        e doencas
        O programa do usuario utiliza essa funcao para verificar se o paciente 
        esta doente e enviar um alerta
        Essa funcao, no caso, esta em uma classe singleton, em q eh treinada pelo
        agente, ao mesmo tempo que é utilizada pelo programa
        esse agente de tempos em tempos acessa uma parte do banco de dados para treinar
        a funcao
        a priori, posso colocar as atividades, sem de fato, estar acessando os dados
        Algo como
        agent - tipo agent -
        select 10 entries from dataset
        train classifier function
        -> e la na classe report, gera uma atividade para classify collected data
        */
    
    public void function(double methane, double hydrogen, double co2, double alcohol);
}
