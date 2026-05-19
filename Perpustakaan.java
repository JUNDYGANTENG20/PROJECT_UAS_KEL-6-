import java.util.*;
import java.io.*;

// Sistem Perpustakaan Sederhana dengan fitur CRUD, sorting dan searching

public class Perpustakaan {

    // ==== DATA SIMPANAN ====
    static List<String[]> daftarBuku = new ArrayList<>();
    static List<String[]> daftarAnggota = new ArrayList<>();
    static List<String[]> daftarPinjam = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        bacaBukuCSV();
        bacaAnggotaCSV();
        bacaPinjamCSV();

        int pilihan;
        do {
            tampilkanMenuLogin();
            pilihan = Integer.parseInt(input.nextLine());
            switch (pilihan) {
                case 1 -> {
                    int menuAdmin;
                    do {
                        tampilkanMenuAdmin();
                        menuAdmin = Integer.parseInt(input.nextLine());
                        switch (menuAdmin) {
                            case 1 -> lihatBuku();
                            case 2 -> tambahBuku();
                            case 3 -> editBuku();
                            case 4 -> hapusBuku();
                            case 5 -> lihatAnggota();
                            case 6 -> tambahAnggota();
                            case 7 -> editAnggota();
                            case 8 -> hapusAnggota();
                            case 9 -> lihatPeminjaman();
                            case 10 -> menuSorting();
                            case 11 -> menuSearching();
                            case 0 -> System.out.println("Keluar dari menu admin...");
                            default -> System.out.println("Pilihan tidak valid!");
                        }
                    } while (menuAdmin != 0);
                }
                case 2 -> {
                    int menuUser;
                    do {
                        tampilkanMenuUser();
                        menuUser = Integer.parseInt(input.nextLine());
                        switch (menuUser) {
                            case 1 -> lihatBuku();
                            case 2 -> pinjamBuku();
                            case 3 -> kembalikanBuku();
                            case 4 -> lihatPeminjaman();
                            case 5 -> menuSorting();
                            case 6 -> menuSearching();
                            case 0 -> System.out.println("Keluar dari menu user...");
                            default -> System.out.println("Pilihan tidak valid!");
                        }
                    } while (menuUser != 0);
                }
                case 0 -> System.out.println("Terima kasih telah menggunakan sistem perpustakaan!");
                default -> System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }

    // ==== MENU LOGIN =====
    static void tampilkanMenuLogin() {
        System.out.println("\n====================================");
        System.out.println("       SISTEM PERPUSTAKAAN");
        System.out.println("====================================");
        System.out.println(" 1. Login sebagai Admin");
        System.out.println(" 2. Login sebagai User");
        System.out.println(" 0. Keluar");
        System.out.println("====================================");
        System.out.print("Pilih opsi: ");
    }

    // ==== MENU ADMIN =====
    static void tampilkanMenuAdmin() {
        System.out.println("\n====================================");
        System.out.println("       SISTEM PERPUSTAKAAN");
        System.out.println("====================================");
        System.out.println(" 1.  Lihat Daftar Buku");
        System.out.println(" 2.  Tambah Buku");
        System.out.println(" 3.  Edit Buku");
        System.out.println(" 4.  Hapus Buku");
        System.out.println(" 5.  Lihat Daftar Anggota");
        System.out.println(" 6.  Tambah Anggota");
        System.out.println(" 7.  Edit Anggota");
        System.out.println(" 8.  Hapus Anggota");
        System.out.println(" 9.  Lihat Data Peminjaman");
        System.out.println(" 10. Urutkan Data (Sorting)");
        System.out.println(" 11. Cari Buku (searching)");
        System.out.println(" 0.  Keluar");
        System.out.println("====================================");
        System.out.print("Pilih menu: ");
    }

    // ==== MENU USER =====
    static void tampilkanMenuUser() {
        System.out.println("\n====================================");
        System.out.println("       SISTEM PERPUSTAKAAN");
        System.out.println("====================================");
        System.out.println(" 1.  Lihat Daftar Buku");
        System.out.println(" 2.  Pinjam Buku");
        System.out.println(" 3.  Kembalikan Buku");
        System.out.println(" 4.  Lihat Data Peminjaman");
        System.out.println(" 5.  Urutkan Data (Sorting)");
        System.out.println(" 6.  Cari Buku (searching)");
        System.out.println(" 0.  Keluar");
        System.out.println("====================================");
        System.out.print("Pilih menu: ");
    }

    // ===== MENU SORTING =====
    static void menuSorting() {
        System.out.println("\n--- MENU FILTER ---");
        System.out.println(" 1. Urutkan Buku berdasarkan Judul (A-Z)");
        System.out.println(" 2. Urutkan Buku berdasarkan ISBN");
        System.out.println(" 3. Urutkan Buku berdasarkan Stok (Terbanyak)");
        System.out.println(" 4. Urutkan Anggota berdasarkan Nama (A-Z)");
        System.out.println(" 5. Urutkan Anggota berdasarkan ID");
        System.out.println(" 6. Urutkan Peminjaman berdasarkan Tanggal");
        System.out.println(" 7. Urutkan Peminjaman berdasarkan Status");
        System.out.print("Pilih sorting: ");

        int pilihan = Integer.parseInt(input.nextLine());
        switch (pilihan) {
            case 1 -> {
                bubbleSortBuku(1);
                lihatBuku();
            }
            case 2 -> {
                bubbleSortBuku(0);
                lihatBuku();
            }
            case 3 -> {
                bubbleSortBukuStok();
                lihatBuku();
            }
            case 4 -> {
                bubbleSortAnggota(1);
                lihatAnggota();
            }
            case 5 -> {
                bubbleSortAnggota(0);
                lihatAnggota();
            }
            case 6 -> {
                bubbleSortPinjam(3);
                lihatPeminjaman();
            }
            case 7 -> {
                bubbleSortPinjam(4);
                lihatPeminjaman();
            }
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    // ====== MENU SEARCHING ======
    static void menuSearching() {
        System.out.println("\n--- MENU CARI BUKU ---");
        System.out.println(" 1. Cari berdasarkan Judul");
        System.out.println(" 2. Cari berdasarkan ISBN");
        System.out.println(" 3. Cari berdasarkan Kategori");
        System.out.println(" 4. Cari berdasarkan Pengarang");
        System.out.print("Pilih opsi: ");
        int pilihan = Integer.parseInt(input.nextLine());

        switch (pilihan) {
            case 1 -> cariBuku();
            case 2 -> cariBukuByISBN();
            case 3 -> cariBukuByKategori();
            case 4 -> cariBukuByPengarang();
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    // ===========================================\\

    // =============================================
    // BUBBLE SORT - BUKU berdasarkan kolom
    // kolom 0 = ISBN, kolom 1 = Judul, kolom 2 = Pengarang
    // =============================================
    static void bubbleSortBuku(int kolom) {
        int n = daftarBuku.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String a = daftarBuku.get(j)[kolom].toLowerCase();
                String b = daftarBuku.get(j + 1)[kolom].toLowerCase();
                if (a.compareTo(b) > 0) {
                    // Tukar posisi
                    String[] temp = daftarBuku.get(j);
                    daftarBuku.set(j, daftarBuku.get(j + 1));
                    daftarBuku.set(j + 1, temp);
                }
            }
        }
        System.out.println("[✓] Buku berhasil diurutkan!");
    }

    // ============================================
    // BUBBLE SORT - BUKU berdasarkan STOK
    // Diurutkan dari stok terbanyak ke sedikit
    // ============================================
    static void bubbleSortBukuStok() {
        int n = daftarBuku.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int stokA = Integer.parseInt(daftarBuku.get(j)[4]);
                int stokB = Integer.parseInt(daftarBuku.get(j + 1)[4]);
                if (stokA < stokB) {
                    // Tukar posisi (descending)
                    String[] temp = daftarBuku.get(j);
                    daftarBuku.set(j, daftarBuku.get(j + 1));
                    daftarBuku.set(j + 1, temp);
                }
            }
        }
        System.out.println("[✓] Buku berhasil diurutkan berdasarkan stok!");
    }

    // ============================================
    // BUBBLE SORT - ANGGOTA (berdasarkan kolom)
    // kolom 0 = ID, kolom 1 = Nama
    // ============================================
    static void bubbleSortAnggota(int kolom) {
        int n = daftarAnggota.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String a = daftarAnggota.get(j)[kolom].toLowerCase();
                String b = daftarAnggota.get(j + 1)[kolom].toLowerCase();
                if (a.compareTo(b) > 0) {
                    // Tukar posisi
                    String[] temp = daftarAnggota.get(j);
                    daftarAnggota.set(j, daftarAnggota.get(j + 1));
                    daftarAnggota.set(j + 1, temp);
                }
            }
        }
        System.out.println("[✓] Anggota berhasil diurutkan!");
    }

    // ============================================
    // BUBBLE SORT - PEMINJAMAN (berdasarkan kolom)
    // kolom 3 = Tanggal, kolom 4 = Status
    // ============================================
    static void bubbleSortPinjam(int kolom) {
        int n = daftarPinjam.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String a = daftarPinjam.get(j)[kolom].toLowerCase();
                String b = daftarPinjam.get(j + 1)[kolom].toLowerCase();
                if (a.compareTo(b) > 0) {
                    // Tukar posisi
                    String[] temp = daftarPinjam.get(j);
                    daftarPinjam.set(j, daftarPinjam.get(j + 1));
                    daftarPinjam.set(j + 1, temp);
                }
            }
        }
        System.out.println("[✓] Peminjaman berhasil diurutkan!");
    }

    // ============================================
    // FUNGSI-FUNGSI LAINNYA
    // ============================================

    // ===== BUKU =====
    static void lihatBuku() {

        System.out.println("\n--- DAFTAR BUKU ---");

        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku.");
            return;
        }

        System.out.printf("%-6s %-25s %-20s %-20s %s%n", "ISBN", "Judul", "Kategori", "Pengarang", "Stok");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
        for (String[] b : daftarBuku) {
            System.out.printf("%-6s %-25s %-20s %-20s %s%n", b[0], b[1], b[2], b[3], b[4]);
        }
    }

    static void tambahBuku() {

        // Validasi input bisa ditambahkan untuk memastikan data yang dimasukkan benar
        // (misal stok harus angka)
        System.out.println("\n--- TAMBAH BUKU ---");
        System.out.print("ISBN     : ");
        String isbn = input.nextLine();
        System.out.print("Judul    : ");
        String judul = input.nextLine();
        System.out.print("Kategori : ");
        String kategori = input.nextLine();
        System.out.print("Pengarang: ");
        String pengarang = input.nextLine();
        System.out.print("Stok     : ");
        String stok = input.nextLine();

        try {
            Integer.parseInt(stok);
        } catch (NumberFormatException e) {
            System.out.println("Stok harus berupa angka!");
            return;
        }

        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) {
                System.out.println("ISBN sudah ada!");
                return;
            }
        }
        daftarBuku.add(new String[] { isbn, judul, kategori, pengarang, stok });
        System.out.println("Buku berhasil ditambahkan!");

        simpanBukuCSV();
    }

    static void editBuku() {
        System.out.println("\n--- EDIT BUKU ---");
        System.out.print("Masukkan ISBN buku yang ingin diedit: ");
        String isbn = input.nextLine();
        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) {
                System.out.print("Judul baru    : ");
                b[1] = input.nextLine();
                System.out.print("Kategori baru : ");
                b[2] = input.nextLine();
                System.out.print("Pengarang baru: ");
                b[3] = input.nextLine();
                System.out.print("Stok baru     : ");
                b[4] = input.nextLine();
                System.out.println("Buku berhasil diedit!");
                simpanBukuCSV();
                return;
            }
        }
        System.out.println("ISBN tidak ditemukan!");
    }

    static void hapusBuku() {
        System.out.println("\n--- HAPUS BUKU ---");
        System.out.print("Masukkan ISBN buku: ");
        String isbn = input.nextLine();
        for (String[] p : daftarPinjam) {
            if (p[2].equals(isbn) && p[4].equals("Dipinjam")) {
                System.out.println("Buku sedang dipinjam, tidak bisa dihapus!");
                return;
            }
        }
        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) {
                daftarBuku.remove(b);
                System.out.println("Buku berhasil dihapus!");
                simpanBukuCSV();
                return;
            }
        }
        System.out.println("ISBN tidak ditemukan!");

    }

    // ====== SEARCHING =====

    static void cariBuku() {
        System.out.print("Masukkan judul buku: ");
        String keyword = input.nextLine().toLowerCase();

        boolean ketemu = false;

        for (String[] b : daftarBuku) {
            if (b[1].toLowerCase().contains(keyword)) {
                System.out.printf("%-6s %-25s %-20s %-20s %s%n",
                        b[0], b[1], b[2], b[3], b[4]);
                ketemu = true;
            }
        }

        if (!ketemu) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    // searching untuk isbn lebih tepatnya karena isbn itu unik, jadi pakai equals
    // saja
    static void cariBukuByISBN() {
        System.out.print("Masukkan ISBN buku: ");
        String isbn = input.nextLine().toLowerCase();

        boolean ketemu = false;

        for (String[] b : daftarBuku) {
            if (b[0].toLowerCase().equals(isbn)) {
                System.out.printf("%-6s %-25s %-20s %-20s %s%n",
                        b[0], b[1], b[2], b[3], b[4]);
                ketemu = true;
            }
        }

        if (!ketemu) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    // seraching untuk bedasarkan kategori atau pengarang bisa ditambahkan dengan
    // cara yang sama seperti cariBuku() hanya saja kolomnya berbeda (kolom 2 untuk
    // kategori, kolom 3 untuk pengarang)
    static void cariBukuByKategori() {
        System.out.print("Masukkan kategori buku: ");
        String keyword = input.nextLine().toLowerCase();

        boolean ketemu = false;

        for (String[] b : daftarBuku) {
            if (b[2].toLowerCase().contains(keyword)) {
                System.out.printf("%-6s %-25s %-20s %-20s %s%n",
                        b[0], b[1], b[2], b[3], b[4]);
                ketemu = true;
            }
        }

        if (!ketemu) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    static void cariBukuByPengarang() {
        System.out.print("Masukkan nama pengarang: ");
        String keyword = input.nextLine().toLowerCase();

        boolean ketemu = false;

        for (String[] b : daftarBuku) {
            if (b[3].toLowerCase().contains(keyword)) {
                System.out.printf("%-6s %-25s %-20s %-20s %s%n",
                        b[0], b[1], b[2], b[3], b[4]);
                ketemu = true;
            }
        }

        if (!ketemu) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    // ===== ANGGOTA =====
    static void lihatAnggota() {
        System.out.println("\n--- DAFTAR ANGGOTA ---");
        if (daftarAnggota.isEmpty()) {
            System.out.println("Tidak ada anggota.");
            return;
        }
        System.out.printf("%-8s %-20s %s%n", "ID", "Nama", "No. Telp");
        System.out.println("-".repeat(45)); 
        // harusnya pake 
        // for (int i = 0; i < 45; i++) {
        // siout("-");
        // } tapi biar cepet aja pakai repeat
        for (String[] a : daftarAnggota) {
            System.out.printf("%-8s %-20s %s%n", a[0], a[1], a[2]);
        }
    }

    static void tambahAnggota() {
        System.out.println("\n--- TAMBAH ANGGOTA ---");
        System.out.print("Nama    : ");
        String nama = input.nextLine();
        System.out.print("No Telp : ");
        String noTelp = input.nextLine();
        String id = "A" + String.format("%03d", daftarAnggota.size() + 1);
        daftarAnggota.add(new String[] { id, nama, noTelp });
        System.out.println("Anggota berhasil didaftarkan! ID: " + id);

        simpanAnggotaCSV();
    }

    static void editAnggota() {
        System.out.println("\n--- EDIT ANGGOTA ---");
        System.out.print("Masukkan ID anggota yang ingin diedit: ");
        String id = input.nextLine();
        for (String[] a : daftarAnggota) {
            if (a[0].equals(id)) {
                System.out.print("Nama baru    : ");
                a[1] = input.nextLine();
                System.out.print("No Telp baru : ");
                a[2] = input.nextLine();
                System.out.println("Anggota berhasil diedit!");
                System.out.println("ID: " + a[0] + ", Nama: " + a[1] + ", No Telp: " + a[2]);
                simpanAnggotaCSV();
                return;
            }
        }
        System.out.println("ID tidak ditemukan!");
    }

    static void hapusAnggota() {
        System.out.println("\n--- HAPUS ANGGOTA ---");
        System.out.print("Masukkan ID anggota: ");
        String id = input.nextLine();
        for (String[] p : daftarPinjam) {
            if (p[1].equals(id) && p[4].equals("Dipinjam")) {
                System.out.println("Anggota masih memiliki pinjaman aktif!");
                return;
            }
        }
        boolean dihapus = daftarAnggota.removeIf(a -> a[0].equals(id));
        System.out.println(dihapus ? "Anggota berhasil dihapus!" : "ID tidak ditemukan!");

        simpanAnggotaCSV();
    }

    // ===== PEMINJAMAN =====
    static void pinjamBuku() {
        System.out.println("\n--- PINJAM BUKU ---");
        System.out.print("ID Anggota : ");
        String idAnggota = input.nextLine();
        System.out.print("ISBN Buku  : ");
        String isbn = input.nextLine();

        boolean anggotaAda = daftarAnggota.stream().anyMatch(a -> a[0].equals(idAnggota));
        if (!anggotaAda) {
            System.out.println("Anggota tidak ditemukan!");
            return;
        }

        String[] buku = null;
        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) {
                buku = b;
                break;
            }
        }
        if (buku == null) {
            System.out.println("Buku tidak ditemukan!");
            return;
        }
        if (Integer.parseInt(buku[4]) <= 0) {
            System.out.println("Stok buku habis!");
            return;
        }

        String idPinjam = "P" + String.format("%03d", daftarPinjam.size() + 1);
        String tgl = java.time.LocalDate.now().toString();
        daftarPinjam.add(new String[] { idPinjam, idAnggota, isbn, tgl, "Dipinjam" });
        buku[4] = String.valueOf(Integer.parseInt(buku[4]) - 1);

        System.out.println("Peminjaman berhasil! ID Pinjam: " + idPinjam);
        System.out.println("Tanggal Pinjam: " + tgl);
        System.out.println("ID Anggota: " + idAnggota);
        System.out.println("ISBN Buku: " + isbn);

        simpanBukuCSV(); // Simpan perubahan stok
        simpanPinjamCSV(); // Simpan data peminjaman baru
    }

    static void kembalikanBuku() {
        System.out.println("\n--- KEMBALIKAN BUKU ---");
        System.out.print("ID Peminjaman: ");
        String idPinjam = input.nextLine();

        for (String[] p : daftarPinjam) {
            if (p[0].equals(idPinjam)) {
                if (p[4].equals("Dikembalikan")) {
                    System.out.println("Buku sudah pernah dikembalikan!");
                    return;
                }
                p[4] = "Dikembalikan";
                for (String[] b : daftarBuku) {
                    if (b[0].equals(p[2])) {
                        b[4] = String.valueOf(Integer.parseInt(b[4]) + 1);
                        break;
                    }
                }
                System.out.println("Buku berhasil dikembalikan!");
                System.out.println("Tanggal Kembali: " + java.time.LocalDate.now());
                System.out.println("ID Peminjaman: " + idPinjam);
                System.out.println("ID Anggota: " + p[1]);
                System.out.println("ISBN Buku: " + p[2]);
                simpanPinjamCSV(); // Simpan perubahan status peminjaman
                return;
            }
        }
        System.out.println("ID Peminjaman tidak ditemukan!");
    }

    static void lihatPeminjaman() {

        System.out.println("\n--- DATA PEMINJAMAN ---");
        if (daftarPinjam.isEmpty()) {
            System.out.println("Belum ada data peminjaman.");
            return;
        }
        System.out.printf("%-8s %-10s %-8s %-14s %s%n", "ID", "ID Anggota", "ISBN", "Tgl Pinjam", "Status");
        System.out.println("-".repeat(55));
        for (String[] p : daftarPinjam) {
            System.out.printf("%-8s %-10s %-8s %-14s %s%n", p[0], p[1], p[2], p[3], p[4]);
        }
    }

    // ====== SIMPAN DATA KE CSV ======
    static void simpanBukuCSV() {
        try {
            FileWriter writer = new FileWriter("databuku.csv");

            for (String[] buku : daftarBuku) {
                writer.write(String.join(",", buku));
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan buku.");
        }
    }

    static void simpanAnggotaCSV() {
        try {
            FileWriter writer = new FileWriter("dataanggota.csv");

            for (String[] anggota : daftarAnggota) {
                writer.write(String.join(",", anggota));
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan anggota.");
        }
    }

    static void simpanPinjamCSV() {
        try {
            FileWriter writer = new FileWriter("datapinjam.csv");

            for (String[] pinjam : daftarPinjam) {
                writer.write(String.join(",", pinjam));
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan peminjaman.");
        }
    }

    // ====== BACA DATA DARI CSV ======
    static void bacaBukuCSV() {
        try {
            File file = new File("databuku.csv");
            if (!file.exists()) {
                return; // Jika file tidak ada, langsung keluar
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                if (data.length == 5) {
                    daftarBuku.add(data);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File buku tidak ditemukan.");
        }
    }

    static void bacaAnggotaCSV() {
        try {
            File file = new File("dataanggota.csv");
            if (!file.exists()) {
                return; // Jika file tidak ada, langsung keluar
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    daftarAnggota.add(data);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File anggota tidak ditemukan.");
        }
    }

    static void bacaPinjamCSV() {
        try {
            File file = new File("datapinjam.csv");
            if (!file.exists()) {
                return; // Jika file tidak ada, langsung keluar, itulah gunanya return.
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                if (data.length == 5) {
                    daftarPinjam.add(data);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File peminjaman tidak ditemukan.");
        }
    }

}