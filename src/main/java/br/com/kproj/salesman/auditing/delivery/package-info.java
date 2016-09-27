
/**
 * Modulo responsavel por gerenciar as alteracoes que uma tarefa no delivery,
 * com isso toda alteracao feita na mesma esse modulo recupera atraves
 * de uma mensagem(recebida no subscriber) verifica se a ultima versao
 * esta diferente da versao atual(vinda na mensagem).
 *
 * Se diferente o modulo salva a nova versao.
 */
package br.com.kproj.salesman.auditing.delivery;