<div align="center">

# 🌌 Arcardium 🌌

### _Um Roguelike Arcano no Terminal_

</div>

> Uma jornada sombria onde cada escolha molda seu poder. Inspirado pela estratégia de **Slay the Spire**, a aleatoriedade de **Balatro** e a atmosfera impiedosa de **Dark Souls**.

---

### ✨ **Sobre o Jogo**

**Arcardium** é uma experiência tática onde você, um mago solitário, explora caminhos gerados proceduralmente. Sua missão é simples: sobreviver, construir um grimório de poder inimaginável e derrotar os Guardiões que o aguardam.

---

### 🕹️ **Mecânicas Principais**

| Ícone | Mecânica |
| :---: | :--- |
| **🎲** | **Roguelike Tático:** Cada run é um novo desafio. A morte é só o começo. |
| **🔥** | **Builds de Magia:** Combine feitiços de dano, buffs, debuffs e venenos. Crie sinergias devastadoras. |
| **⚔️** | **Combate em Grupo:** Enfrente múltiplos inimigos. A escolha do alvo é a chave para a vitória. |
| **🗺️** | **Exploração:** Navegue por salas de **Combate**, **Eventos** de sorte e azar, e **Repousos** estratégicos. |
| **🃏** | **Mini-Jogos:** Teste seu destino com cartas de Tarô e outros jogos arcanos. |

---

### 🗺️ **Roadmap do Projeto**

#### ✅ **Concluído**
- [x] Estrutura Base do Jogo e Menu
- [x] Sistema de XP e Níveis
- [x] Mapa Procedural com Encontros
- [x] Arquétipos de Mago
- [x] Sistema de Efeitos (Buffs/Debuffs)
- [x] Combate em Grupo (1 vs N)
- [x] Seleção de Alvo
- [x] Eventos (Tarô) e Salas de Repouso

#### ✅ **Concluído**
- [X] Efeitos com Identidade (Veneno, Fogo, etc.)
- [x] IA e Habilidades para Inimigos
- [x] Gerenciamento do Grimório (Limite e Recompensas Únicas)
- [x] Sistema de Agilidade (Ordem de Turno)
- [x] Expansão de Conteúdo (Magias e Arquétipos)

#### 😎 **Foco atual**
- [x] Loja e Sistema de Ouro
- [x] Mecânica de Invocação
- [ ] Metaprogresso e Desbloqueios
- [ ] Novos Atos, Chefes e Eventos
- [ ] Melhorias de Interface e Polimento Visual

---
### 🚀 **Como Executar**

Siga estas instruções para configurar e executar o projeto em sua máquina local.

#### **Pré-requisitos**

Você precisará ter o Git e o Apache Maven instalados em seu computador.

* **Git:** É um sistema de controle de versão distribuído.
    * [Instruções de download e instalação para Windows, macOS e Linux](https://git-scm.com/downloads)
* **Apache Maven:** É uma ferramenta de automação de compilação usada principalmente para projetos Java.
    * [Instruções de download e instalação](https://maven.apache.org/download.cgi)
    * Certifique-se de que o Java Development Kit (JDK) esteja instalado, pois o Maven é uma ferramenta baseada em Java.

#### **Passos para Execução**

1.  **Clonar o Repositório:**
    Abra seu terminal ou prompt de comando e use o seguinte comando para clonar o projeto:
    ```sh
    git clone [https://github.com/ericfr1tzenvalle/oop-arcardium-project.git](https://github.com/ericfr1tzenvalle/oop-arcardium-project.git)
    ```

2.  **Navegar para o Diretório do Projeto:**
    Após a clonagem, acesse a pasta do projeto:
    ```sh
    cd oop-arcardium-project
    ```

3.  **Compilar e Empacotar o Projeto:**
    Use o Maven para compilar o código-fonte e empacotá-lo em um arquivo `.jar` executável. Este comando também irá baixar todas as dependências necessárias.
    ```sh
    mvn clean package
    ```

4.  **Executar o Jogo:**
    Após a conclusão do empacotamento, você encontrará o arquivo `.jar` no diretório `target`. Execute o jogo com o seguinte comando:
    ```sh
    java -jar target/Arcardium-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
---

<div align="center">

## 📄 Licença

Licença [MIT](LICENSE). A magia é livre para todos.

</div>
