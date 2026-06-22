import java.io.*;
import java.util.*;
import javax.swing.*;

public class Ficheiros extends JFrame {

    public Ficheiros() {}

    public void writeText(Vector v, String file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + v);
            bw.close();
            JOptionPane.showMessageDialog(null, "Text file sucessfully written.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in writing some characters");
        }
    }

    public void writeObject(Vector v) {
        try {
            FileOutputStream fi = new FileOutputStream("LAM Object.dat");
            ObjectOutputStream ob = new ObjectOutputStream(fi);
            ob.writeObject(v);
            ob.close();
            JOptionPane.showMessageDialog(null, "File Object sucessfully written.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error while writing.\n" + e.getMessage());
        }
    }

    // Formato: idProduct | tipo | name | descricao | categoria | price | amountStock | validade
    public void read(Vector v, String nomeFicheiro) {
        Produto pr;
        StringTokenizer str;
        String nome, tipo, descricao, categoria, validade, linha = "";
        short id, amount;
        float preco;

        try {
            FileReader fr = new FileReader(nomeFicheiro);
            BufferedReader br = new BufferedReader(fr);
            linha = br.readLine();

            while (linha != null) {
                str = new StringTokenizer(linha, "|");
                id        = Short.parseShort(str.nextToken().trim());
                tipo      = str.nextToken().trim();
                nome      = str.nextToken().trim();
                descricao = str.nextToken().trim();
                categoria = str.nextToken().trim();
                preco     = Float.parseFloat(str.nextToken().trim());
                amount    = Short.parseShort(str.nextToken().trim());
                validade  = str.nextToken().trim();

                pr = new Produto(id, tipo, nome, descricao, categoria, preco, amount, validade);
                v.add(pr);
                linha = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "ERROR!\nThere was a mistake while reading the file.\n" + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro de conversao numerica.\nLinha: " + linha + "\n" + e.getMessage());
        }
    }

    // Carrega todos os ficheiros de produtos para um único vector
    public Vector<Produto> carregarTodosProdutos() {
        Vector<Produto> produtos = new Vector<>();
        String[] fichs = {
                "Cuidados Pessoais.txt",
                "Beverages.txt",
                "Limpezas e Casa.txt",
                "Lacticios e Refrigerados.txt",
                "Alimentos Congelados.txt"
        };
        for (String f : fichs) read(produtos, f);
        return produtos;
    }

    // Formato Fornecedor.txt: nome | contacto | endereco | metodoDePagamento | mercado
    public Vector<Fornecedor> carregarFornecedores() {
        Vector<Fornecedor> fornecedores = new Vector<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Fornecedor.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(" \\| ");
                if (p.length >= 5)
                    fornecedores.add(new Fornecedor(
                            p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(), p[4].trim()));
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Nao foi possivel encontrar o ficheiro dos fornecedores.\n" + e.getMessage());
        }
        return fornecedores;
    }

    // CORRECÇÃO: tipo de retorno alterado de Vector<Cliente> para Vector<Object[]>
    // porque nao existe classe Cliente — cada linha é guardada como String[]
    // que é um subtipo de Object[], compatível com Vector<Object[]>.
    public Vector<Object[]> carregarClientes() {
        Vector<Object[]> clientes = new Vector<>();
        String[] fichs = {"Supermercado.txt", "Retalho.txt", "Horeca.txt"};
        for (String f : fichs) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String linha;
                while ((linha = br.readLine()) != null) {
                    // Dividir pelo delimitador " | " usado nos ficheiros de clientes
                    String[] partes = linha.split(" \\| ");
                    clientes.add(partes);
                }
                br.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao ler o ficheiro " + f + ".\n" + e.getMessage());
            }
        }
        return clientes;
    }
}