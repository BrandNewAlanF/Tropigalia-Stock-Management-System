import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
class Gestao extends JFrame implements GestaoView {
    private static final Color BRANCO_GELO    = new Color(248, 249, 250);
    private static final Color VERDE_SALVIA   = new Color(125, 193, 154);
    private static final Color VERDE_ABACATE  = new Color(46,  204, 113);
    private static final Color VERDE_FLORESTA = new Color(30,   58,  39);
    private static final Color CORAL_ALERTA   = new Color(217, 107,  98);
    private static final Color BRANCO         = Color.WHITE;
    private static final Color CINZA_SUAVE    = new Color(107, 123, 111);

    // Constantes para alertas de reposição
    private static final int LIMITE_REPOSICAO = 100;
    private static final double IVA_REPOSICAO = 0.03; // 3%
    private static final double IVA_VENDA = 0.17;     // 17%

    //Vectores para cada tipo de objecto
    private Vector<Produto> vectorProdutos = new Vector<>();
    private Vector<Fornecedor> vectorFornecedores = new Vector<>();
    private Vector<Object[]> vectorClientes = new Vector<>();


    private Ficheiros fi = new Ficheiros();

    // Paineis
    private JPanel painelSidebar;
    private JPanel painelPrincipal;
    private JPanel painelDashboard;
    private JPanel painelProdutos;
    private JPanel painelFornecer;
    private JPanel painelVendas;
    private JPanel painelFornecedores;
    private JPanel painelClientes;
    private JPanel painelRelatorio;
    private JPanel painelAjuda;

    // Componentes do sidebar
    private JLabel lblLogo;
    private JLabel navDashboard,
            navProdutos,
            navFornecer,
            navVendas,
            navFornecedores,
            navClientes,
            navRelatorio,
            navAjuda,
            navLogout;
    private JLabel lblNomeUtilizador;
    private JLabel lblTituloTopbar;

    //Painel para a barra de cima
    private JPanel painelTopbar;
    private RoundedButton btnNotificacoes;
    private RoundedButton btnVoltar;

    // Labels do DashBoard
    private JLabel lblValorTotalStock;
    private JLabel lblValorProdutosActivos;
    private JLabel lblValorAlertas;
    private JLabel lblValorFornecedores;

    // Tabelas p/ o painel de Dashboard
    private JTable tabelaStocks;
    private JScrollPane scrollStocks;
    private JTable tabelaAlertas;
    private JScrollPane scrollAlertas;

    // ── Painel Produtos ─────────────────────────────────────────────
    private JTextField campoProdID, campoProdNome, campoProdTipo,
            campoProdDesc, campoProdCat, campoProdPreco,
            campoProdQtd, campoProdValidade;
    private JTable tabelaProdutos;
    private RoundedButton btnAdicionarProd, btnRemoverProd, btnGuardarProd;

    // ── Painel Fornecer (entrada de stock) ───────────────────────────
    private JComboBox<String> comboFornProduto;
    private JTextField campoFornQtd, campoFornFornecedor, campoFornData;
    private JComboBox<String> comboFornMetodo;
    private RoundedButton btnSubmeterForn, btnCancelarForn;

    // ── Painel Vendas (saída de stock) ───────────────────────────────
    private JComboBox<String> comboVendProduto;
    private JTextField campoVendQtd, campoVendCliente, campoVendData;
    private JLabel lblVendPrecoUnitario, lblVendTotal;
    private RoundedButton btnSubmeterVenda, btnCancelarVenda;

    // ── Painel Fornecedores ─────────────────────────────────────────
    private JTable tabelaFornecedores;
    private JScrollPane scrollFornecedores;
    private JTextField campoFornNome, campoFornContacto, campoFornEndereco,
            campoFornMetodoPag, campoFornMercado;
    private RoundedButton btnAdicionarForn;

    // ── Painel Clientes ─────────────────────────────────────────────
    private JTable tabelaClientes;
    private JScrollPane scrollClientes;

    // ── Painel Relatório ────────────────────────────────────────────
    private JTable tabelaRelatorio;
    private JScrollPane scrollRelatorio;
    private JComboBox<String> comboTipoRelatorio;

    private JTable tabelaPilha;
    private JScrollPane scrollPilha;
    private RoundedButton btnDesfazer;
    private Stack<String> pilhaOperacoes = new Stack<>();
    private GestaoPresenter presenter;
    // ── Painel Ajuda ────────────────────────────────────────────────
    private JTextArea areaAjuda;
    private JScrollPane scrollAjuda;

    // ── Separadores da sidebar ──────────────────────────────────────
    private JSeparator sep1, sep2;
    private DecimalFormat mt;
    private final String textoAjuda =
            "TROPIGÁLIA — Sistema de Gestão de Stocks\n" +
                    "ISCTEM | Algoritmos e Estruturas de Dados II\n\n" +
                    "FUNCIONALIDADES:\n" +
                    "• Dashboard: visão geral do stock, alertas de validade e reposição.\n" +
                    "• Produtos: cadastro, adição e remoção de produtos.\n" +
                    "• Entrada de Stock: regista compras e actualiza quantidades automaticamente.\n" +
                    "• Saída / Vendas: regista vendas com controlo de preço mínimo de venda.\n" +
                    "• Fornecedores: cadastro de fornecedores com contacto e condições de pagamento.\n" +
                    "• Clientes: lista de clientes B2B (Supermercado, Retalho, Horeca).\n" +
                    "• Relatórios: relatórios de stock, alertas de validade e reposição.\n\n" +
                    "REGRAS DO SISTEMA:\n" +
                    "• O preço de venda nunca pode ser inferior ao preço de compra.\n" +
                    "• Produtos com stock abaixo de " + LIMITE_REPOSICAO + " unidades geram alerta.\n" +
                    "• Produtos vencidos são sinalizados como lixo perigoso.\n" +
                    "• Todas as operações ficam registadas na Pilha (TAD Pilha).\n\n" +
                    "CONTACTOS DE APOIO:\n" +
                    "  +258 85 235 8235\n" +
                    "  alannhanala406@gmail.com ";

    Gestao(String userName) {
        super("Tropigália — Gestão de Stocks");
        setSize(960, 620);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BRANCO_GELO);
        getContentPane().setLayout(null);

        mt= new DecimalFormat("###,###,###.00 mzn");
        presenter = new GestaoPresenter(this);
        carregarTodosProdutos();
        carregarFornecedores();
        carregarClientes();

        construirSidebar(userName);
        construirTopbar();
        construirPainelPrincipal();
        construirDashboard();
        construirPainelProdutos();
        construirPainelFornecer();
        construirPainelVendas();
        construirPainelFornecedores();
        construirPainelClientes();
        construirPainelRelatorio();
        construirPainelAjuda();

        mostrarPainel("Dashboard");
        setVisible(true);
    }


    //Metodos para a construcao dos Sidebars
    private void construirSidebar(String userName) {
        painelSidebar = new JPanel(null);
        painelSidebar.setBounds(0, 0, 200, 620);
        painelSidebar.setBackground(VERDE_FLORESTA);
        getContentPane().add(painelSidebar);

        lblLogo = new JLabel("TROPIGÁLIA");
        lblLogo.setFont(new Font("Serif", Font.BOLD, 15));
        lblLogo.setForeground(BRANCO);
        lblLogo.setBounds(20, 18, 160, 30);
        painelSidebar.add(lblLogo);

        sep1 = new JSeparator();
        sep1.setBounds(10, 52, 180, 1);
        sep1.setForeground(new Color(80, 110, 90));
        sep1.setBackground(new Color(80, 110, 90));
        painelSidebar.add(sep1);

        int yNav = 66;
        navDashboard    = criarItemNav("  Dashboard",       yNav); yNav += 34;
        navProdutos     = criarItemNav("  Produtos",         yNav); yNav += 34;
        navFornecer     = criarItemNav("  Entrada de Stock", yNav); yNav += 34;
        navVendas       = criarItemNav("  Saída / Vendas",   yNav); yNav += 34;
        navFornecedores = criarItemNav("  Fornecedores",     yNav); yNav += 34;
        navClientes     = criarItemNav("  Clientes",         yNav); yNav += 34;
        navRelatorio    = criarItemNav("  Relatórios",       yNav); yNav += 34;
        navAjuda        = criarItemNav("  Ajuda",            yNav);

        painelSidebar.add(navDashboard);
        painelSidebar.add(navProdutos);
        painelSidebar.add(navFornecer);
        painelSidebar.add(navVendas);
        painelSidebar.add(navFornecedores);
        painelSidebar.add(navClientes);
        painelSidebar.add(navRelatorio);
        painelSidebar.add(navAjuda);

        sep2 = new JSeparator();
        sep2.setBounds(10, 530, 180, 1);
        sep2.setForeground(new Color(80, 110, 90));
        sep2.setBackground(new Color(80, 110, 90));
        painelSidebar.add(sep2);


        JPanel avatar = new JPanel(new BorderLayout());
        avatar.setBounds(14, 540, 32, 32);
        avatar.setBackground(VERDE_SALVIA);
        String ini = userName != null && !userName.isEmpty()
                ? ("" + userName.charAt(0)).toUpperCase() : "U";
        JLabel lblIni = new JLabel(ini, SwingConstants.CENTER);
        lblIni.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblIni.setForeground(VERDE_FLORESTA);
        avatar.add(lblIni);
        painelSidebar.add(avatar);

        lblNomeUtilizador = new JLabel(userName != null ? userName : "Utilizador");
        lblNomeUtilizador.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblNomeUtilizador.setForeground(new Color(210, 225, 215));
        lblNomeUtilizador.setBounds(52, 540, 135, 16);
        painelSidebar.add(lblNomeUtilizador);

        JLabel lblCargo = new JLabel("Gestor de Stock");
        lblCargo.setFont(new Font("SansSerif", Font.PLAIN, 9));
        lblCargo.setForeground(new Color(130, 160, 140));
        lblCargo.setBounds(52, 556, 135, 13);
        painelSidebar.add(lblCargo);

        navLogout = new JLabel("Terminar Sessão");
        navLogout.setFont(new Font("SansSerif", Font.PLAIN, 11));
        navLogout.setForeground(CORAL_ALERTA);
        navLogout.setBounds(0, 580, 200, 30);
        navLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navLogout.setOpaque(true);
        navLogout.setBackground(VERDE_FLORESTA);
        navLogout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { acaoLogout(); }
        });
        painelSidebar.add(navLogout);

        adicionarListenerNav(navDashboard,    "Dashboard");
        adicionarListenerNav(navProdutos,     "Produtos");
        adicionarListenerNav(navFornecer,     "Fornecer");
        adicionarListenerNav(navVendas,       "Vendas");
        adicionarListenerNav(navFornecedores, "Fornecedores");
        adicionarListenerNav(navClientes,     "Clientes");
        adicionarListenerNav(navRelatorio,    "Relatorio");
        adicionarListenerNav(navAjuda,        "Ajuda");
    }

    private void adicionarListenerNav(JLabel item, String painel) {
        item.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { mostrarPainel(painel); }
        });
    }

    private JLabel criarItemNav(String texto, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(new Color(200, 215, 205));
        lbl.setBounds(0, y, 200, 30);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setOpaque(true);
        lbl.setBackground(VERDE_FLORESTA);
        return lbl;
    }


    private void destacarNavAtivo(JLabel navAtivo) {
        JLabel[] todos = {navDashboard, navProdutos, navFornecer, navVendas,
                navFornecedores, navClientes, navRelatorio, navAjuda};
        for (JLabel nav : todos) {
            nav.setBackground(VERDE_FLORESTA);
            nav.setForeground(new Color(200, 215, 205));
            nav.setBorder(null);
        }
        if (navAtivo != null) {
            navAtivo.setBackground(new Color(38, 82, 56));
            navAtivo.setForeground(VERDE_ABACATE);
            navAtivo.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, VERDE_ABACATE));
        }
    }

    private void construirTopbar() {
        painelTopbar = new JPanel(null);
        painelTopbar.setBounds(200, 0, 760, 50);
        painelTopbar.setBackground(BRANCO);
        painelTopbar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
                new Color(30, 58, 39, 40)));
        getContentPane().add(painelTopbar);

        lblTituloTopbar = new JLabel("Dashboard — Visão Geral");
        lblTituloTopbar.setFont(new Font("Serif", Font.BOLD, 16));
        lblTituloTopbar.setForeground(VERDE_FLORESTA);
        lblTituloTopbar.setBounds(20, 10, 450, 30);
        painelTopbar.add(lblTituloTopbar);

        btnNotificacoes = new RoundedButton("🔔", 8);
        btnNotificacoes.setBounds(695, 12, 38, 28);
        btnNotificacoes.setBackground(BRANCO_GELO);
        btnNotificacoes.setForeground(VERDE_FLORESTA);
        btnNotificacoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNotificacoes.addActionListener(e -> mostrarAlertas());
        painelTopbar.add(btnNotificacoes);

        btnVoltar = new RoundedButton("✖ Voltar", 8);
        btnVoltar.setBounds(608, 12, 80, 28);
        btnVoltar.setBackground(CORAL_ALERTA);
        btnVoltar.setForeground(BRANCO);
        btnVoltar.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> { new Sessao(); dispose(); });
        painelTopbar.add(btnVoltar);
    }


    private void construirPainelPrincipal() {
        painelPrincipal = new JPanel(new CardLayout());
        painelPrincipal.setBounds(200, 50, 760, 570);
        painelPrincipal.setBackground(BRANCO_GELO);
        getContentPane().add(painelPrincipal);
    }


    private void construirDashboard() {
        painelDashboard = new JPanel(null);
        painelDashboard.setBackground(BRANCO_GELO);

        JPanel cValor    = criarCartaoKPI("Valor Total em Stock (MT)", "—", "calculado");
        JPanel cProd     = criarCartaoKPI("Produtos Activos",           "—", "referências");
        JPanel cAlertas  = criarCartaoKPI("Alertas de Reposição",       "—", "abaixo do mínimo");
        JPanel cForn     = criarCartaoKPI("Fornecedores",               "—", "registados");
        cValor.setBounds(15,  15, 175, 90);
        cProd.setBounds(200,  15, 175, 90);
        cAlertas.setBounds(385, 15, 175, 90);
        cForn.setBounds(570,  15, 175, 90);

        lblValorTotalStock      = (JLabel) cValor.getComponent(1);
        lblValorProdutosActivos = (JLabel) cProd.getComponent(1);
        lblValorAlertas         = (JLabel) cAlertas.getComponent(1);
        lblValorFornecedores    = (JLabel) cForn.getComponent(1);

        painelDashboard.add(cValor);
        painelDashboard.add(cProd);
        painelDashboard.add(cAlertas);
        painelDashboard.add(cForn);

        // Tabela de Stocks
        JPanel secaoStocks = new JPanel(null);
        secaoStocks.setBounds(15, 120, 730, 200);
        secaoStocks.setBackground(BRANCO);
        secaoStocks.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel tSt = criarTituloSecao("  Gestão de Stocks");
        tSt.setBounds(0, 0, 730, 36);
        secaoStocks.add(tSt);

        String[] colunasStocks = {"ID","Tipo","Nome","Categoria","Preço (MT)","Qtd","Validade","Estado"};
        tabelaStocks = new JTable(new DefaultTableModel(null, colunasStocks) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        estilizarTabela(tabelaStocks);
        scrollStocks = new JScrollPane(tabelaStocks);
        scrollStocks.setBounds(5, 38, 720, 155);
        scrollStocks.setBorder(null);
        secaoStocks.add(scrollStocks);
        painelDashboard.add(secaoStocks);

        // Tabela de Alertas
        JPanel secaoAlertas = new JPanel(null);
        secaoAlertas.setBounds(15, 335, 730, 210);
        secaoAlertas.setBackground(BRANCO);
        secaoAlertas.setBorder(BorderFactory.createLineBorder(CORAL_ALERTA));
        JLabel tAl = criarTituloSecao("Alertas — Validade Vencida & Reposição Urgente");
        tAl.setForeground(CORAL_ALERTA);
        tAl.setBounds(0, 0, 730, 36);
        tAl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CORAL_ALERTA));
        secaoAlertas.add(tAl);

        String[] colunasAlertas = {"Nome do Produto","Categoria","Qtd em Stock","Validade","Motivo do Alerta"};
        tabelaAlertas = new JTable(new DefaultTableModel(null, colunasAlertas) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        tabelaAlertas.setForeground(CORAL_ALERTA);
        estilizarTabela(tabelaAlertas);
        tabelaAlertas.setForeground(CORAL_ALERTA);
        scrollAlertas = new JScrollPane(tabelaAlertas);
        scrollAlertas.setBounds(5, 38, 720, 164);
        scrollAlertas.setBorder(null);
        secaoAlertas.add(scrollAlertas);
        painelDashboard.add(secaoAlertas);

        painelPrincipal.add(painelDashboard, "Dashboard");
        actualizarDashboard();
    }

    private void construirPainelProdutos() {
        painelProdutos = new JPanel(null);
        painelProdutos.setBackground(BRANCO_GELO);

        JPanel cartaoTab = new JPanel(null);
        cartaoTab.setBounds(15, 15, 730, 300);
        cartaoTab.setBackground(BRANCO);
        cartaoTab.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel t = criarTituloSecao("  Inventário de Produtos");
        t.setBounds(0, 0, 730, 36);
        cartaoTab.add(t);

        String[] cols = {"ID","Tipo","Nome","Descrição","Categoria","Preço (MT)","Qtd","Validade"};
        tabelaProdutos = new JTable(new DefaultTableModel(null, cols) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        estilizarTabela(tabelaProdutos);
        JScrollPane sp = new JScrollPane(tabelaProdutos);
        sp.setBounds(5, 38, 720, 225);
        sp.setBorder(null);
        cartaoTab.add(sp);

        btnGuardarProd = new RoundedButton("↺ Actualizar Lista", 8);
        btnGuardarProd.setBounds(5, 268, 160, 28);
        estilizarBotaoPrimario(btnGuardarProd);
        btnGuardarProd.addActionListener(e -> { preencherTabelaProdutos(); actualizarDashboard(); });
        cartaoTab.add(btnGuardarProd);
        painelProdutos.add(cartaoTab);

        JPanel cartaoCad = new JPanel(null);
        cartaoCad.setBounds(15, 328, 730, 218);
        cartaoCad.setBackground(BRANCO);
        cartaoCad.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel tCad = criarTituloSecao("  Adicionar / Editar Produto");
        tCad.setBounds(0, 0, 730, 36);
        cartaoCad.add(tCad);

        campoProdID       = criarCampoComLabel(cartaoCad, "ID",          15,  60,  75, 28);
        campoProdTipo     = criarCampoComLabel(cartaoCad, "Tipo",        105,  60, 110, 28);
        campoProdNome     = criarCampoComLabel(cartaoCad, "Nome",        230,  60, 165, 28);
        campoProdDesc     = criarCampoComLabel(cartaoCad, "Descrição",   410,  60, 200, 28);
        campoProdCat      = criarCampoComLabel(cartaoCad, "Categoria",    15, 130, 135, 28);
        campoProdPreco    = criarCampoComLabel(cartaoCad, "Preço (MT)",  165, 130, 100, 28);
        campoProdQtd      = criarCampoComLabel(cartaoCad, "Quantidade",  280, 130,  95, 28);
        campoProdValidade = criarCampoComLabel(cartaoCad, "Validade",    390, 130, 115, 28);

        btnAdicionarProd = new RoundedButton("+ Adicionar", 8);
        btnAdicionarProd.setBounds(520, 118, 110, 30);
        estilizarBotaoPrimario(btnAdicionarProd);
        btnAdicionarProd.addActionListener(e -> acaoAdicionarProduto());
        cartaoCad.add(btnAdicionarProd);

        btnRemoverProd = new RoundedButton("Remover", 8);
        btnRemoverProd.setBounds(520, 155, 110, 30);
        btnRemoverProd.setBackground(BRANCO);
        btnRemoverProd.setForeground(CORAL_ALERTA);
        btnRemoverProd.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnRemoverProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRemoverProd.addActionListener(e -> acaoRemoverProduto());
        cartaoCad.add(btnRemoverProd);

        painelProdutos.add(cartaoCad);
        preencherTabelaProdutos();
        painelPrincipal.add(painelProdutos, "Produtos");
    }


    private void construirPainelFornecer() {
        painelFornecer = new JPanel(null);
        painelFornecer.setBackground(BRANCO_GELO);

        JPanel cartao = new JPanel(null);
        cartao.setBounds(15, 15, 730, 400);
        cartao.setBackground(BRANCO);
        cartao.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel tit = criarTituloSecao("  Entrada de Stock — Registo de Compra");
        tit.setBounds(0, 0, 730, 36);
        cartao.add(tit);

        JLabel lProd = criarLabelFormulario("Produto");
        lProd.setBounds(20, 54, 200, 14);
        cartao.add(lProd);
        comboFornProduto = new JComboBox<>();
        comboFornProduto.addItem("Seleccionar produto...");
        for (int i = 0; i < vectorProdutos.size(); i++)
            comboFornProduto.addItem(vectorProdutos.get(i).getNome());
        comboFornProduto.setBounds(20, 70, 340, 28);
        estilizarCombo(comboFornProduto);
        cartao.add(comboFornProduto);

        campoFornQtd = criarCampoComLabel(cartao, "Quantidade a Adicionar", 380, 70, 150, 28);
        campoFornFornecedor = criarCampoComLabel(cartao, "Fornecedor", 20, 140, 340, 28);

        JLabel lMet = criarLabelFormulario("Método de Pagamento");
        lMet.setBounds(380, 126, 220, 14);
        cartao.add(lMet);
        comboFornMetodo = new JComboBox<>(new String[]{
                "Seleccionar...", "Numerário", "Conta Bancária (BCI)",
                "Conta Bancária (Millennium BIM)", "Banca Móvel (M-Pesa)",
                "Banca Móvel (e-Mola)", "Cheque"
        });
        comboFornMetodo.setBounds(380, 142, 220, 28);
        estilizarCombo(comboFornMetodo);
        cartao.add(comboFornMetodo);

        campoFornData = criarCampoComLabel(cartao, "Data (dd/MM/yyyy)", 20, 210, 200, 28);

        btnCancelarForn = new RoundedButton("Cancelar", 8);
        btnCancelarForn.setBounds(20, 270, 190, 34);
        btnCancelarForn.setBackground(BRANCO);
        btnCancelarForn.setForeground(CORAL_ALERTA);
        btnCancelarForn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCancelarForn.setBorder(BorderFactory.createLineBorder(CORAL_ALERTA));
        btnCancelarForn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelarForn.addActionListener(e -> limparPainelFornecer());
        cartao.add(btnCancelarForn);

        btnSubmeterForn = new RoundedButton("✔ Registar Entrada de Stock", 8);
        btnSubmeterForn.setBounds(220, 270, 270, 34);
        estilizarBotaoPrimario(btnSubmeterForn);
        btnSubmeterForn.addActionListener(e -> acaoRegistarEntradaStock());
        cartao.add(btnSubmeterForn);

        painelFornecer.add(cartao);
        painelPrincipal.add(painelFornecer, "Fornecer");
    }


    private void construirPainelVendas() {
        painelVendas = new JPanel(null);
        painelVendas.setBackground(BRANCO_GELO);

        JPanel cartao = new JPanel(null);
        cartao.setBounds(15, 15, 730, 400);
        cartao.setBackground(BRANCO);
        cartao.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel tit = criarTituloSecao("  Saída de Stock — Registo de Venda");
        tit.setBounds(0, 0, 730, 36);
        cartao.add(tit);

        JLabel lProd = criarLabelFormulario("Produto");
        lProd.setBounds(20, 54, 200, 14);
        cartao.add(lProd);
        comboVendProduto = new JComboBox<>();
        comboVendProduto.addItem("Seleccionar produto...");
        for (int i = 0; i < vectorProdutos.size(); i++)
            comboVendProduto.addItem(vectorProdutos.get(i).getNome());
        comboVendProduto.setBounds(20, 70, 340, 28);
        estilizarCombo(comboVendProduto);
        cartao.add(comboVendProduto);

        campoVendQtd = criarCampoComLabel(cartao, "Quantidade a Vender", 380, 70, 150, 28);

        JLabel lPU = criarLabelFormulario("Preço Unitário (MT)");
        lPU.setBounds(20, 118, 180, 14);
        cartao.add(lPU);
        lblVendPrecoUnitario = new JLabel("—");
        lblVendPrecoUnitario.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblVendPrecoUnitario.setForeground(VERDE_FLORESTA);
        lblVendPrecoUnitario.setBounds(20, 134, 180, 28);
        cartao.add(lblVendPrecoUnitario);

        JLabel lTot = criarLabelFormulario("Total da Venda (MT)");
        lTot.setBounds(220, 118, 180, 14);
        cartao.add(lTot);
        lblVendTotal = new JLabel("—");
        lblVendTotal.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblVendTotal.setForeground(VERDE_ABACATE);
        lblVendTotal.setBounds(220, 134, 180, 28);
        cartao.add(lblVendTotal);

        comboVendProduto.addActionListener(e -> calcularTotalVenda());
        campoVendQtd.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { calcularTotalVenda(); }
        });

        campoVendCliente = criarCampoComLabel(cartao, "Cliente",          20, 210, 340, 28);
        campoVendData    = criarCampoComLabel(cartao, "Data (dd/MM/yyyy)",380, 210, 200, 28);

        btnCancelarVenda = new RoundedButton("Cancelar", 8);
        btnCancelarVenda.setBounds(20, 270, 190, 34);
        btnCancelarVenda.setBackground(BRANCO);
        btnCancelarVenda.setForeground(CORAL_ALERTA);
        btnCancelarVenda.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCancelarVenda.setBorder(BorderFactory.createLineBorder(CORAL_ALERTA));
        btnCancelarVenda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelarVenda.addActionListener(e -> limparPainelVendas());
        cartao.add(btnCancelarVenda);

        btnSubmeterVenda = new RoundedButton("✔ Registar Venda", 8);
        btnSubmeterVenda.setBounds(220, 270, 220, 34);
        estilizarBotaoPrimario(btnSubmeterVenda);
        btnSubmeterVenda.addActionListener(e -> acaoRegistarVenda());
        cartao.add(btnSubmeterVenda);

        painelVendas.add(cartao);
        painelPrincipal.add(painelVendas, "Vendas");
    }

    private void construirPainelFornecedores() {
        painelFornecedores = new JPanel(null);
        painelFornecedores.setBackground(BRANCO_GELO);

        JPanel cartaoTab = new JPanel(null);
        cartaoTab.setBounds(15, 15, 730, 280);
        cartaoTab.setBackground(BRANCO);
        cartaoTab.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel t = criarTituloSecao("  Fornecedores Registados");
        t.setBounds(0, 0, 730, 36);
        cartaoTab.add(t);

        String[] cols = {"Nome","Contacto","Endereço","Método de Pagamento","Mercado"};
        tabelaFornecedores = new JTable(new DefaultTableModel(null, cols) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        estilizarTabela(tabelaFornecedores);
        scrollFornecedores = new JScrollPane(tabelaFornecedores);
        scrollFornecedores.setBounds(5, 38, 720, 234);
        scrollFornecedores.setBorder(null);
        cartaoTab.add(scrollFornecedores);
        painelFornecedores.add(cartaoTab);

        JPanel cartaoCad = new JPanel(null);
        cartaoCad.setBounds(15, 308, 730, 210);
        cartaoCad.setBackground(BRANCO);
        cartaoCad.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel tCad = criarTituloSecao("  Adicionar Fornecedor");
        tCad.setBounds(0, 0, 730, 36);
        cartaoCad.add(tCad);

        campoFornNome      = criarCampoComLabel(cartaoCad, "Nome",              15,  55, 200, 28);
        campoFornContacto  = criarCampoComLabel(cartaoCad, "Contacto",         230,  55, 160, 28);
        campoFornMercado   = criarCampoComLabel(cartaoCad, "Mercado",           410,  55, 200, 28);
        campoFornEndereco  = criarCampoComLabel(cartaoCad, "Endereço",           15, 125, 370, 28);
        campoFornMetodoPag = criarCampoComLabel(cartaoCad, "Método Pagamento",  400, 125, 200, 28);

        btnAdicionarForn = new RoundedButton("+ Adicionar Fornecedor", 8);
        btnAdicionarForn.setBounds(15, 168, 220, 30);
        estilizarBotaoPrimario(btnAdicionarForn);
        btnAdicionarForn.addActionListener(e -> acaoAdicionarFornecedor());
        cartaoCad.add(btnAdicionarForn);

        painelFornecedores.add(cartaoCad);
        preencherTabelaFornecedores();
        painelPrincipal.add(painelFornecedores, "Fornecedores");
    }



    private void construirPainelClientes() {
        painelClientes = new JPanel(null);
        painelClientes.setBackground(BRANCO_GELO);

        JPanel cartao = new JPanel(null);
        cartao.setBounds(15, 15, 730, 520);
        cartao.setBackground(BRANCO);
        cartao.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel t = criarTituloSecao("  Clientes B2B — Supermercado | Retalho | Horeca");
        t.setBounds(0, 0, 730, 36);
        cartao.add(t);

        String[] cols = {"NUIT","Nome","Tipo","Endereço","E-mail","Contacto","Info Extra"};
        tabelaClientes = new JTable(new DefaultTableModel(null, cols) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        estilizarTabela(tabelaClientes);
        scrollClientes = new JScrollPane(tabelaClientes);
        scrollClientes.setBounds(5, 38, 720, 474);
        scrollClientes.setBorder(null);
        cartao.add(scrollClientes);
        painelClientes.add(cartao);

        preencherTabelaClientes();
        painelPrincipal.add(painelClientes, "Clientes");
    }

    private void construirPainelRelatorio() {
        painelRelatorio = new JPanel(null);
        painelRelatorio.setBackground(BRANCO_GELO);

        JPanel cartao = new JPanel(null);
        cartao.setBounds(15, 15, 730, 520);
        cartao.setBackground(BRANCO);
        cartao.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel t = criarTituloSecao("  Relatórios e Análise");
        t.setBounds(0, 0, 730, 36);
        cartao.add(t);

        JLabel lTipo = criarLabelFormulario("Tipo de Relatório");
        lTipo.setBounds(15, 50, 200, 14);
        cartao.add(lTipo);

        comboTipoRelatorio = new JComboBox<>(new String[]{
                "Relatório de Stock Actual",
                "Alertas de Validade (Lixo Perigoso)",
                "Relatório de Reposição",
                "Lista de Fornecedores",
                "Lista de Clientes B2B"
        });
        comboTipoRelatorio.setBounds(15, 66, 300, 28);
        estilizarCombo(comboTipoRelatorio);
        cartao.add(comboTipoRelatorio);

        RoundedButton btnGerar = new RoundedButton("▶ Gerar Relatório", 8);
        btnGerar.setBounds(326, 66, 170, 28);
        estilizarBotaoPrimario(btnGerar);
        btnGerar.addActionListener(e -> gerarRelatorio());
        cartao.add(btnGerar);

        String[] colsRel = {"Col 1","Col 2","Col 3","Col 4","Col 5"};
        tabelaRelatorio = new JTable(new DefaultTableModel(null, colsRel) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        estilizarTabela(tabelaRelatorio);
        scrollRelatorio = new JScrollPane(tabelaRelatorio);
        scrollRelatorio.setBounds(5, 106, 720, 200);
        scrollRelatorio.setBorder(null);
        cartao.add(scrollRelatorio);

        painelRelatorio.add(cartao);
        JLabel lblPilha = criarTituloSecao("  Histórico de Operações");
        lblPilha.setBounds(5, 320, 720, 36);
        cartao.add(lblPilha);

        String[] colsPilha = {"Topo", "Operação"};
        tabelaPilha = new JTable(new DefaultTableModel(null, colsPilha) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });

        estilizarTabela(tabelaPilha);

        scrollPilha = new JScrollPane(tabelaPilha);
        scrollPilha.setBounds(5, 360, 720, 110);
        scrollPilha.setBorder(null);

        cartao.add(scrollPilha);

        btnDesfazer = new RoundedButton("↩ Desfazer Última Operação", 8);
        btnDesfazer.setBounds(5, 480, 250, 30);

        estilizarBotaoPrimario(btnDesfazer);

        btnDesfazer.addActionListener(e -> desfazerUltimaOperacao());

        cartao.add(btnDesfazer);
        painelPrincipal.add(painelRelatorio, "Relatorio");
    }


    private void construirPainelAjuda() {
        painelAjuda = new JPanel(null);
        painelAjuda.setBackground(BRANCO_GELO);

        JPanel cartao = new JPanel(null);
        cartao.setBounds(15, 15, 730, 530);
        cartao.setBackground(BRANCO);
        cartao.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel t = criarTituloSecao("  Ajuda — Tropigália Sistema de Gestão");
        t.setBounds(0, 0, 730, 36);
        cartao.add(t);

        areaAjuda = new JTextArea(textoAjuda);
        areaAjuda.setFont(new Font("SansSerif", Font.PLAIN, 12));
        areaAjuda.setForeground(VERDE_FLORESTA);
        areaAjuda.setBackground(BRANCO);
        areaAjuda.setEditable(false);
        areaAjuda.setLineWrap(true);
        areaAjuda.setWrapStyleWord(true);
        areaAjuda.setMargin(new Insets(14, 14, 14, 14));
        scrollAjuda = new JScrollPane(areaAjuda);
        scrollAjuda.setBounds(10, 44, 710, 480);
        scrollAjuda.setBorder(null);
        cartao.add(scrollAjuda);

        painelAjuda.add(cartao);
        painelPrincipal.add(painelAjuda, "Ajuda");
    }

    private void acaoAdicionarFornecedor() {
        presenter.adicionarFornecedor();
    }

    private void carregarTodosProdutos() {
        vectorProdutos = fi.carregarTodosProdutos();
    }

    private void carregarFornecedores() {
        vectorFornecedores = fi.carregarFornecedores();
    }

    private void carregarClientes() {
        vectorClientes = fi.carregarClientes();
    }

    private void estilizarTabela(JTable t) {
        EstiloVisual.estilizarTabela(t);
    }

    private void estilizarBotaoPrimario(RoundedButton btn) {
        EstiloVisual.estilizarBotaoPrimario(btn);
    }

    private void estilizarCombo(JComboBox<?> c) {
        EstiloVisual.estilizarCombo(c);
    }

    private JLabel criarTituloSecao(String texto) {
        return FabricaComponentes.criarTituloSecao(texto);
    }

    private JLabel criarLabelFormulario(String texto) {
        return FabricaComponentes.criarLabelFormulario(texto);
    }

    private JTextField criarCampoComLabel(JPanel pai, String label,
                                          int x, int y, int w, int h) {
        return FabricaComponentes.criarCampoComLabel(pai, label, x, y, w, h);
    }

    private JPanel criarCartaoKPI(String label, String valor, String sub) {
        return FabricaComponentes.criarCartaoKPI(label, valor, sub);
    }

    private boolean campoVazio(String texto) {
        return Validador.campoVazio(texto);
    }

    private boolean comboValido(JComboBox<?> combo) {
        return Validador.comboValido(combo);
    }

    private boolean dataValida(String data) {
        return Validador.dataValida(data);
    }

    private boolean numeroPositivo(String texto) {
        return Validador.numeroPositivo(texto);
    }

    private void limparCamposProduto() {
        Formularios.limparCamposProduto(campoProdID, campoProdTipo, campoProdNome,
                campoProdDesc, campoProdCat, campoProdPreco, campoProdQtd, campoProdValidade);
    }

    private void limparPainelFornecer() {
        Formularios.limparPainelFornecer(comboFornProduto, campoFornQtd,
                campoFornFornecedor, comboFornMetodo, campoFornData);
    }

    private void limparPainelVendas() {
        Formularios.limparPainelVendas(comboVendProduto, campoVendQtd,
                campoVendCliente, campoVendData, lblVendPrecoUnitario, lblVendTotal);
    }

    private void preencherTabelaClientes() {
        GestorDeTabelas.preencherTabelaClientes(tabelaClientes, vectorClientes);
    }

    private void preencherTabelaFornecedores() {
        GestorDeTabelas.preencherTabelaFornecedores(tabelaFornecedores, vectorFornecedores);
    }

    private void preencherTabelaProdutos() {
        GestorDeTabelas.preencherTabelaProdutos(tabelaProdutos, vectorProdutos, mt);
    }

    private void actualizarTabelaPilha() {
        GestaoDaPila.actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);
    }

    private void desfazerUltimaOperacao() {
        presenter.desfazerUltimaOperacao();
    }

    private boolean estaVencido(String validade, String hoje) {
        return DashboardService.estaVencido(validade, hoje);
    }

    public void actualizarDashboard() {
        DashboardService.actualizarDashboard(tabelaStocks, tabelaAlertas,
                lblValorTotalStock, lblValorProdutosActivos, lblValorAlertas,
                lblValorFornecedores, vectorProdutos, vectorFornecedores, mt,
                LIMITE_REPOSICAO);
    }

    private void acaoAdicionarProduto() {
        presenter.adicionarProduto();
    }

    private void acaoRemoverProduto() {
        presenter.removerProduto();
    }

    private void acaoRegistarEntradaStock() {
        presenter.registarEntradaStock();
    }

    private void acaoRegistarVenda() {
        presenter.registarVenda();
    }

    private void calcularTotalVenda() {
        presenter.calcularTotalVenda();
    }

    private void gerarRelatorio() {
        presenter.gerarRelatorio();
    }

    private void mostrarAlertas() {
        presenter.mostrarAlertas();
    }

    public void actualizarCombos() {
        comboFornProduto.removeAllItems();
        comboFornProduto.addItem("Seleccionar produto...");
        comboVendProduto.removeAllItems();
        comboVendProduto.addItem("Seleccionar produto...");
        for (int i = 0; i < vectorProdutos.size(); i++) {
            String n = vectorProdutos.get(i).getNome();
            comboFornProduto.addItem(n);
            comboVendProduto.addItem(n);
        }
    }

    public JFrame getFrame() { return this; }
    public Vector<Produto> getProdutos() { return vectorProdutos; }
    public Vector<Fornecedor> getFornecedores() { return vectorFornecedores; }
    public Vector<Object[]> getClientes() { return vectorClientes; }
    public Stack<String> getPilhaOperacoes() { return pilhaOperacoes; }
    public DecimalFormat getMoedaFormat() { return mt; }
    public int getLimiteReposicao() { return LIMITE_REPOSICAO; }
    public double getIvaReposicao() { return IVA_REPOSICAO; }
    public double getIvaVenda() { return IVA_VENDA; }

    public JTextField getCampoProdID() { return campoProdID; }
    public JTextField getCampoProdTipo() { return campoProdTipo; }
    public JTextField getCampoProdNome() { return campoProdNome; }
    public JTextField getCampoProdDesc() { return campoProdDesc; }
    public JTextField getCampoProdCat() { return campoProdCat; }
    public JTextField getCampoProdPreco() { return campoProdPreco; }
    public JTextField getCampoProdQtd() { return campoProdQtd; }
    public JTextField getCampoProdValidade() { return campoProdValidade; }
    public JTable getTabelaProdutos() { return tabelaProdutos; }

    public JComboBox<String> getComboFornProduto() { return comboFornProduto; }
    public JTextField getCampoFornQtd() { return campoFornQtd; }
    public JTextField getCampoFornFornecedor() { return campoFornFornecedor; }
    public JComboBox<String> getComboFornMetodo() { return comboFornMetodo; }
    public JTextField getCampoFornData() { return campoFornData; }

    public JComboBox<String> getComboVendProduto() { return comboVendProduto; }
    public JTextField getCampoVendQtd() { return campoVendQtd; }
    public JTextField getCampoVendCliente() { return campoVendCliente; }
    public JTextField getCampoVendData() { return campoVendData; }
    public JLabel getLblVendPrecoUnitario() { return lblVendPrecoUnitario; }
    public JLabel getLblVendTotal() { return lblVendTotal; }

    public JTextField getCampoFornNome() { return campoFornNome; }
    public JTextField getCampoFornContacto() { return campoFornContacto; }
    public JTextField getCampoFornEndereco() { return campoFornEndereco; }
    public JTextField getCampoFornMetodoPag() { return campoFornMetodoPag; }
    public JTextField getCampoFornMercado() { return campoFornMercado; }
    public JTable getTabelaFornecedores() { return tabelaFornecedores; }

    public JTable getTabelaClientes() { return tabelaClientes; }
    public JTable getTabelaStocks() { return tabelaStocks; }
    public JTable getTabelaAlertas() { return tabelaAlertas; }
    public JLabel getLblValorTotalStock() { return lblValorTotalStock; }
    public JLabel getLblValorProdutosActivos() { return lblValorProdutosActivos; }
    public JLabel getLblValorAlertas() { return lblValorAlertas; }
    public JLabel getLblValorFornecedores() { return lblValorFornecedores; }

    public JTable getTabelaRelatorio() { return tabelaRelatorio; }
    public JComboBox<String> getComboTipoRelatorio() { return comboTipoRelatorio; }
    public JTable getTabelaPilha() { return tabelaPilha; }

    private void acaoLogout() {
        int r = JOptionPane.showConfirmDialog(this, "Deseja mesmo terminar a sessão?",
                "Terminar Sessão", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Sessão terminada. Progresso guardado!");
            pilhaOperacoes.clear();
            new Welcome();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Boa decisão. Continue as suas actividades.");
        }
    }

    public void mostrarPainel(String nome) {
        CardLayout cl = (CardLayout) painelPrincipal.getLayout();
        cl.show(painelPrincipal, nome);

        String[] paineis = {"Dashboard","Produtos","Fornecer","Vendas",
                "Fornecedores","Clientes","Relatorio","Ajuda"};
        String[] titulos = {
                "Dashboard — Visão Geral",
                "Produtos — Cadastro e Inventário",
                "Entrada de Stock — Registo de Compra",
                "Saída / Vendas — Registo de Venda",
                "Fornecedores — Cadastro",
                "Clientes B2B",
                "Relatórios e Análise",
                "Ajuda"
        };
        JLabel[] navItems = {navDashboard, navProdutos, navFornecer, navVendas,
                navFornecedores, navClientes, navRelatorio, navAjuda};

        JLabel activo = null;
        for (int i = 0; i < paineis.length; i++) {
            if (nome.equals(paineis[i])) {
                lblTituloTopbar.setText(titulos[i]);
                activo = navItems[i];
                break;
            }
        }
        destacarNavAtivo(activo);
    }


}
