Exercicio 1.

Notas:
 Objetos Mock -> simulam o comportamento de objetos reais de forma controlada. São normalmente criados para testar o comportamento de outros objetos.



Exercício 2.
alinea b)
 O servico que deve ser "mocked" é o ISimpleHttpClient.
 No teste devemos confirmar algumas operações tais como:
- existe criação correta do url
- método espera pela resposta da API
- parsing do resultado é correto
- através do resultado, a construção do address é correto


alinea c) & d)
O teste whenCorrectAlboiGps_returnAddress, está a testar:
- que faz a invocação ao serviço remoto
- que dado um json válido, consegue fazer o parsing
- consigo pegar no resultado do parsing e retornar o address


Notas: 
 - Classes que acabam em Test, o maven considera como testes unitários. Sao executadas sempre, em todas as build
 - Classes que acabam em IT, o maven considera como testes de integracao. Sao apenas consideradas quando é dito específicamente.
