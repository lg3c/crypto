**_[college project]_**

# crypto

## AES
Para o algoritmo AES, foram utilizadas funções das bibliotecas java.security e javax.crypto (com provedor Bouncy Castle) para geração do vetor de inicialização e da chave, respectivamente. A chave, quando gerada pelo sistema, possui 128 bits.
Existe uma classe de controle (AES) entre a interface e o algoritmo propriamente dito, e a encriptação/decriptação de cada modo está implementada nas classes AesEcb, AesCbc e AesCtr, todas herdeiras da classe AesSuper, que contém atributos e métodos utilizados por todas elas.
Todos os campos só aceitam caracteres hexadecimais, e optou-se por não utilizar padding (apesar do padding com ‘0’ estar disponível com a mudança de apenas um parâmetro no código) para se obter os resultados exatamente como apresentados na especificação FIPS PUB 197.

## RSA
A implementação do algoritmo foi feita utilizando a biblioteca Bouncy Castle – geração do par de chaves (pública e privada, 512 bits, nos formatos default), encriptação e decriptação.
Optou-se por permitir a entrada de texto alfanumérico livre no campo de texto às claras, para tornar mais interessante o uso da ferramenta.

## DSA
A implementação do algoritmo foi feita utilizando a biblioteca Bouncy Castle – geração do par de chaves (pública e privada, 1024 bits, nos formatos default), hash (SHA1 ou SHA-256), assinatura e verificação.
As etapas de geração do hash e assinatura/verificação foram feitas separadamente, pois as instâncias da classe de assinatura que já geram o hash automaticamente (algoritmos SHA1withDSA e SHA256withDSA) não pemitem o acesso ao hash. Portanto, foi feito o hash com a classe específica de resumo criptográfico, e em seguida utilizada uma instância da classe de assinatura com o algoritmo NONEwithDSA.
Optou-se por permitir a entrada de texto alfanumérico livre no campo de texto às claras, para tornar mais interessante o uso da ferramenta.
O sucesso ou fracasso da verificação da assinatura são exibidos na forma de uma mensagem em um dialog ao usuário.

## HMAC
A implementação do algoritmo foi feita utilizando a biblioteca Bouncy Castle – geração da chave (tamanho default, 160 bits para SHA1 e 256 bits para SHA-256) e autenticação nos modos SHA1 ou SHA-256.
Foi a implementação mais simples de se realizar dentre todas as deste projeto.
Optou-se por permitir a entrada de texto alfanumérico livre no campo de texto às claras, para tornar mais interessante o uso da ferramenta.
