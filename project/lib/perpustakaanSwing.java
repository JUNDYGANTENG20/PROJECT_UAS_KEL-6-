import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class perpustakaanSwing extends JFrame {

    DefaultTableModel modelBuku, modelAnggota, modelPinjam;
    JTable tabelBuku, tabelAnggota, tabelPinjam;

    JTextField txtIsbn, txtJudul, txtKategori, txtPengarang, txtStok, txtCariBuku;
    JTextField txtNama, txtTelp, txtCariAnggota;
    JTextField txtIdAnggotaPinjam, txtIsbnPinjam, txtIdKembali;

    public perpustakaanSwing() {
        setTitle("Sistem Manajemen Perpustakaan");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tab = new JTabbedPane();

        tab.addTab("Data Buku", panelBuku());
        tab.addTab("Data Anggota", panelAnggota());
        tab.addTab("Peminjaman", panelPinjam());

        add(tab);

        loadBukuCSV();
        loadAnggotaCSV();
        loadPinjamCSV();

        setVisible(true);
    }

    JPanel panelBuku() {
        JPanel panel = new JPanel(new BorderLayout());

        modelBuku = new DefaultTableModel(new String[]{"ISBN", "Judul", "Kategori", "Pengarang", "Stok"}, 0);
        tabelBuku = new JTable(modelBuku);
        panel.add(new JScrollPane(tabelBuku), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(3, 5, 5, 5));

        txtIsbn = new JTextField();
        txtJudul = new JTextField();
        txtKategori = new JTextField();
        txtPengarang = new JTextField();
        txtStok = new JTextField();
        txtCariBuku = new JTextField();

        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnCari = new JButton("Cari");
        JButton btnRefresh = new JButton("Refresh");

        form.add(new JLabel("ISBN"));
        form.add(new JLabel("Judul"));
        form.add(new JLabel("Kategori"));
        form.add(new JLabel("Pengarang"));
        form.add(new JLabel("Stok"));

        form.add(txtIsbn);
        form.add(txtJudul);
        form.add(txtKategori);
        form.add(txtPengarang);
        form.add(txtStok);

        form.add(btnTambah);
        form.add(btnEdit);
        form.add(btnHapus);
        form.add(txtCariBuku);
        form.add(btnCari);

        JPanel bawah = new JPanel(new BorderLayout());
        bawah.add(form, BorderLayout.CENTER);
        bawah.add(btnRefresh, BorderLayout.SOUTH);

        panel.add(bawah, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahBuku());
        btnEdit.addActionListener(e -> editBuku());
        btnHapus.addActionListener(e -> hapusBuku());
        btnCari.addActionListener(e -> cariBuku());
        btnRefresh.addActionListener(e -> {
            modelBuku.setRowCount(0);
            loadBukuCSV();
        });

        tabelBuku.getSelectionModel().addListSelectionListener(e -> {
            int row = tabelBuku.getSelectedRow();
            if (row != -1) {
                txtIsbn.setText(modelBuku.getValueAt(row, 0).toString());
                txtJudul.setText(modelBuku.getValueAt(row, 1).toString());
                txtKategori.setText(modelBuku.getValueAt(row, 2).toString());
                txtPengarang.setText(modelBuku.getValueAt(row, 3).toString());
                txtStok.setText(modelBuku.getValueAt(row, 4).toString());
            }
        });

        return panel;
    }

    JPanel panelAnggota() {
        JPanel panel = new JPanel(new BorderLayout());

        modelAnggota = new DefaultTableModel(new String[]{"ID", "Nama", "No Telp"}, 0);
        tabelAnggota = new JTable(modelAnggota);
        panel.add(new JScrollPane(tabelAnggota), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));

        txtNama = new JTextField();
        txtTelp = new JTextField();
        txtCariAnggota = new JTextField();

        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnCari = new JButton("Cari");

        form.add(new JLabel("Nama"));
        form.add(new JLabel("No Telp"));
        form.add(new JLabel("Cari"));
        form.add(new JLabel("Aksi"));

        form.add(txtNama);
        form.add(txtTelp);
        form.add(txtCariAnggota);
        form.add(btnCari);

        JPanel tombol = new JPanel();
        tombol.add(btnTambah);
        tombol.add(btnEdit);
        tombol.add(btnHapus);

        panel.add(form, BorderLayout.NORTH);
        panel.add(tombol, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahAnggota());
        btnEdit.addActionListener(e -> editAnggota());
        btnHapus.addActionListener(e -> hapusAnggota());
        btnCari.addActionListener(e -> cariAnggota());

        tabelAnggota.getSelectionModel().addListSelectionListener(e -> {
            int row = tabelAnggota.getSelectedRow();
            if (row != -1) {
                txtNama.setText(modelAnggota.getValueAt(row, 1).toString());
                txtTelp.setText(modelAnggota.getValueAt(row, 2).toString());
            }
        });

        return panel;
    }

    JPanel panelPinjam() {
        JPanel panel = new JPanel(new BorderLayout());

        modelPinjam = new DefaultTableModel(new String[]{"ID Pinjam", "ID Anggota", "ISBN", "Tanggal", "Status"}, 0);
        tabelPinjam = new JTable(modelPinjam);
        panel.add(new JScrollPane(tabelPinjam), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(2, 3, 5, 5));

        txtIdAnggotaPinjam = new JTextField();
        txtIsbnPinjam = new JTextField();
        txtIdKembali = new JTextField();

        JButton btnPinjam = new JButton("Pinjam Buku");
        JButton btnKembali = new JButton("Kembalikan Buku");

        form.add(new JLabel("ID Anggota"));
        form.add(new JLabel("ISBN Buku"));
        form.add(new JLabel("ID Peminjaman"));

        form.add(txtIdAnggotaPinjam);
        form.add(txtIsbnPinjam);
        form.add(txtIdKembali);

        JPanel tombol = new JPanel();
        tombol.add(btnPinjam);
        tombol.add(btnKembali);

        panel.add(form, BorderLayout.NORTH);
        panel.add(tombol, BorderLayout.SOUTH);

        btnPinjam.addActionListener(e -> pinjamBuku());
        btnKembali.addActionListener(e -> kembalikanBuku());

        return panel;
    }

    void tambahBuku() {
        String isbn = txtIsbn.getText();
        String judul = txtJudul.getText();
        String kategori = txtKategori.getText();
        String pengarang = txtPengarang.getText();
        String stok = txtStok.getText();

        if (isbn.isEmpty() || judul.isEmpty() || stok.isEmpty()) {
            pesan("ISBN, Judul, dan Stok wajib diisi!");
            return;
        }

        try {
            Integer.parseInt(stok);
        } catch (Exception e) {
            pesan("Stok harus angka!");
            return;
        }

        modelBuku.addRow(new Object[]{isbn, judul, kategori, pengarang, stok});
        saveBukuCSV();
        clearBuku();
    }

    void editBuku() {
        int row = tabelBuku.getSelectedRow();
        if (row == -1) {
            pesan("Pilih buku dulu!");
            return;
        }

        modelBuku.setValueAt(txtIsbn.getText(), row, 0);
        modelBuku.setValueAt(txtJudul.getText(), row, 1);
        modelBuku.setValueAt(txtKategori.getText(), row, 2);
        modelBuku.setValueAt(txtPengarang.getText(), row, 3);
        modelBuku.setValueAt(txtStok.getText(), row, 4);

        saveBukuCSV();
        clearBuku();
    }

    void hapusBuku() {
        int row = tabelBuku.getSelectedRow();
        if (row == -1) {
            pesan("Pilih buku dulu!");
            return;
        }

        modelBuku.removeRow(row);
        saveBukuCSV();
        clearBuku();
    }

    void cariBuku() {
        String keyword = txtCariBuku.getText().toLowerCase();
        modelBuku.setRowCount(0);

        try (Scanner sc = new Scanner(new File("databuku.csv"))) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                if (data.length == 5 &&
                        (data[0].toLowerCase().contains(keyword) ||
                         data[1].toLowerCase().contains(keyword) ||
                         data[2].toLowerCase().contains(keyword) ||
                         data[3].toLowerCase().contains(keyword))) {
                    modelBuku.addRow(data);
                }
            }
        } catch (Exception e) {
            pesan("Gagal mencari buku!");
        }
    }

    void tambahAnggota() {
        String nama = txtNama.getText();
        String telp = txtTelp.getText();

        if (nama.isEmpty() || telp.isEmpty()) {
            pesan("Nama dan No Telp wajib diisi!");
            return;
        }

        String id = generateIdAnggota();
        modelAnggota.addRow(new Object[]{id, nama, telp});
        saveAnggotaCSV();

        txtNama.setText("");
        txtTelp.setText("");
    }

    void editAnggota() {
        int row = tabelAnggota.getSelectedRow();
        if (row == -1) {
            pesan("Pilih anggota dulu!");
            return;
        }

        modelAnggota.setValueAt(txtNama.getText(), row, 1);
        modelAnggota.setValueAt(txtTelp.getText(), row, 2);
        saveAnggotaCSV();
    }

    void hapusAnggota() {
        int row = tabelAnggota.getSelectedRow();
        if (row == -1) {
            pesan("Pilih anggota dulu!");
            return;
        }

        modelAnggota.removeRow(row);
        saveAnggotaCSV();
    }

    void cariAnggota() {
        String keyword = txtCariAnggota.getText().toLowerCase();
        modelAnggota.setRowCount(0);

        try (Scanner sc = new Scanner(new File("dataanggota.csv"))) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                if (data.length == 3 &&
                        (data[0].toLowerCase().contains(keyword) ||
                         data[1].toLowerCase().contains(keyword))) {
                    modelAnggota.addRow(data);
                }
            }
        } catch (Exception e) {
            pesan("Gagal mencari anggota!");
        }
    }

    void pinjamBuku() {
        String idAnggota = txtIdAnggotaPinjam.getText();
        String isbn = txtIsbnPinjam.getText();

        if (!anggotaAda(idAnggota)) {
            pesan("Anggota tidak ditemukan!");
            return;
        }

        int rowBuku = cariRowBuku(isbn);
        if (rowBuku == -1) {
            pesan("Buku tidak ditemukan!");
            return;
        }

        int stok = Integer.parseInt(modelBuku.getValueAt(rowBuku, 4).toString());
        if (stok <= 0) {
            pesan("Stok buku habis!");
            return;
        }

        String idPinjam = generateIdPinjam();
        String tanggal = LocalDate.now().toString();

        modelPinjam.addRow(new Object[]{idPinjam, idAnggota, isbn, tanggal, "Dipinjam"});
        modelBuku.setValueAt(String.valueOf(stok - 1), rowBuku, 4);

        savePinjamCSV();
        saveBukuCSV();

        pesan("Peminjaman berhasil! ID: " + idPinjam);
    }

    void kembalikanBuku() {
        String idKembali = txtIdKembali.getText();

        for (int i = 0; i < modelPinjam.getRowCount(); i++) {
            if (modelPinjam.getValueAt(i, 0).toString().equals(idKembali)) {
                if (modelPinjam.getValueAt(i, 4).toString().equals("Dikembalikan")) {
                    pesan("Buku sudah dikembalikan!");
                    return;
                }

                String isbn = modelPinjam.getValueAt(i, 2).toString();
                int rowBuku = cariRowBuku(isbn);

                if (rowBuku != -1) {
                    int stok = Integer.parseInt(modelBuku.getValueAt(rowBuku, 4).toString());
                    modelBuku.setValueAt(String.valueOf(stok + 1), rowBuku, 4);
                }

                modelPinjam.setValueAt("Dikembalikan", i, 4);
                savePinjamCSV();
                saveBukuCSV();
                pesan("Buku berhasil dikembalikan!");
                return;
            }
        }

        pesan("ID peminjaman tidak ditemukan!");
    }

    boolean anggotaAda(String id) {
        for (int i = 0; i < modelAnggota.getRowCount(); i++) {
            if (modelAnggota.getValueAt(i, 0).toString().equals(id)) {
                return true;
            }
        }
        return false;
    }

    int cariRowBuku(String isbn) {
        for (int i = 0; i < modelBuku.getRowCount(); i++) {
            if (modelBuku.getValueAt(i, 0).toString().equals(isbn)) {
                return i;
            }
        }
        return -1;
    }

    String generateIdAnggota() {
        int max = 0;
        for (int i = 0; i < modelAnggota.getRowCount(); i++) {
            try {
                int nomor = Integer.parseInt(modelAnggota.getValueAt(i, 0).toString().substring(1));
                if (nomor > max) max = nomor;
            } catch (Exception ignored) {}
        }
        return "A" + String.format("%03d", max + 1);
    }

    String generateIdPinjam() {
        int max = 0;
        for (int i = 0; i < modelPinjam.getRowCount(); i++) {
            try {
                int nomor = Integer.parseInt(modelPinjam.getValueAt(i, 0).toString().substring(1));
                if (nomor > max) max = nomor;
            } catch (Exception ignored) {}
        }
        return "P" + String.format("%03d", max + 1);
    }

    void loadBukuCSV() {
        loadCSV("databuku.csv", modelBuku, 5);
    }

    void loadAnggotaCSV() {
        loadCSV("dataanggota.csv", modelAnggota, 3);
    }

    void loadPinjamCSV() {
        loadCSV("datapinjam.csv", modelPinjam, 5);
    }

    void loadCSV(String namaFile, DefaultTableModel model, int jumlahKolom) {
        try {
            File file = new File(namaFile);
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                if (data.length == jumlahKolom) {
                    model.addRow(data);
                }
            }
            sc.close();
        } catch (Exception e) {
            pesan("Gagal membaca " + namaFile);
        }
    }

    void saveBukuCSV() {
        saveCSV("databuku.csv", modelBuku);
    }

    void saveAnggotaCSV() {
        saveCSV("dataanggota.csv", modelAnggota);
    }

    void savePinjamCSV() {
        saveCSV("datapinjam.csv", modelPinjam);
    }

    void saveCSV(String namaFile, DefaultTableModel model) {
        try (FileWriter writer = new FileWriter(namaFile)) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) writer.write(",");
                }
                writer.write("\n");
            }
        } catch (Exception e) {
            pesan("Gagal menyimpan " + namaFile);
        }
    }

    void clearBuku() {
        txtIsbn.setText("");
        txtJudul.setText("");
        txtKategori.setText("");
        txtPengarang.setText("");
        txtStok.setText("");
    }

    void pesan(String isi) {
        JOptionPane.showMessageDialog(this, isi);
    }

    public static void main(String[] args) {
        new perpustakaanSwing();
    }
}