import java.util.*;

public class Perpustakaan {

    static List<String[]> daftarBuku    = new ArrayList<>();
    static List<String[]> daftarAnggota = new ArrayList<>();
    static List<String[]> daftarPinjam  = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Data awal buku
        daftarBuku.add(new String[]{"003", "Harry Potter",    "J.K. Rowling",      "5"});
        daftarBuku.add(new String[]{"001", "Laskar Pelangi",  "Andrea Hirata",      "3"});
        daftarBuku.add(new String[]{"002", "Bumi Manusia",    "Pramoedya Ananta",   "2"});
        daftarBuku.add(new String[]{"004", "Atomic Habits",   "James Clear",        "4"});


        // data awal User
        daftarAnggota.add(new String[]{"A001", "Siti Rahayu",  "08987654321"});
        daftarAnggota.add(new String[]{"A002", "Budi Santoso", "08123456789"});
        daftarAnggota.add(new String[]{"A003", "Andi Wijaya",  "08111222333"});


        int pilihan;
        do {
            tampilkanMenu();
            pilihan = Integer.parseInt(input.nextLine());
            switch (pilihan) {
                case 1  -> lihatBuku();
                case 2  -> tambahBuku();
                case 3  -> hapusBuku();
                case 4  -> lihatAnggota();
                case 5  -> tambahAnggota();
                case 6  -> hapusAnggota();
                case 7  -> pinjamBuku();
                case 8  -> kembalikanBuku();
                case 9  -> lihatPeminjaman();
                case 10 -> menuSorting();       // ← MENU SORTING BARU
                case 0  -> System.out.println("Terima kasih. Program selesai.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }

    // ===== MENU UTAMA =====
    static void tampilkanMenu() {
        System.out.println("\n====================================");
        System.out.println("       SISTEM PERPUSTAKAAN");
        System.out.println("====================================");
        System.out.println(" 1.  Lihat Daftar Buku");
        System.out.println(" 2.  Tambah Buku");
        System.out.println(" 3.  Hapus Buku");
        System.out.println(" 4.  Lihat Daftar Anggota");
        System.out.println(" 5.  Tambah Anggota");
        System.out.println(" 6.  Hapus Anggota");
        System.out.println(" 7.  Pinjam Buku");
        System.out.println(" 8.  Kembalikan Buku");
        System.out.println(" 9.  Lihat Data Peminjaman");
        System.out.println(" 10. Urutkan Data (Sorting)");
        System.out.println(" 0.  Keluar");
        System.out.println("====================================");
        System.out.print("Pilih menu: ");
    }

    // ===== MENU SORTING =====
    static void menuSorting() {
        System.out.println("\n--- MENU SORTING ---");
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
            case 1 -> { bubbleSortBuku(1);    lihatBuku(); }
            case 2 -> { bubbleSortBuku(0);    lihatBuku(); }
            case 3 -> { bubbleSortBukuStok(); lihatBuku(); }
            case 4 -> { bubbleSortAnggota(1); lihatAnggota(); }
            case 5 -> { bubbleSortAnggota(0); lihatAnggota(); }
            case 6 -> { bubbleSortPinjam(3);  lihatPeminjaman(); }
            case 7 -> { bubbleSortPinjam(4);  lihatPeminjaman(); }
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    // ============================================
    //   BUBBLE SORT - BUKU (berdasarkan kolom)
    //   kolom 0 = ISBN, kolom 1 = Judul
    // ============================================
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
    //   BUBBLE SORT - BUKU berdasarkan STOK
    //   Diurutkan dari stok terbanyak ke sedikit
    // ============================================
    static void bubbleSortBukuStok() {
        int n = daftarBuku.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int stokA = Integer.parseInt(daftarBuku.get(j)[3]);
                int stokB = Integer.parseInt(daftarBuku.get(j + 1)[3]);
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
    //   BUBBLE SORT - ANGGOTA (berdasarkan kolom)
    //   kolom 0 = ID, kolom 1 = Nama
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
    //   BUBBLE SORT - PEMINJAMAN (berdasarkan kolom)
    //   kolom 3 = Tanggal, kolom 4 = Status
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

    // ===== BUKU =====
    static void lihatBuku() {
        System.out.println("\n--- DAFTAR BUKU ---");
        if (daftarBuku.isEmpty()) { System.out.println("Tidak ada buku."); return; }
        System.out.printf("%-6s %-25s %-20s %s%n", "ISBN", "Judul", "Pengarang", "Stok");
        System.out.println("-".repeat(60));
        for (String[] b : daftarBuku) {
            System.out.printf("%-6s %-25s %-20s %s%n", b[0], b[1], b[2], b[3]);
        }
    }

    static void tambahBuku() {
        System.out.println("\n--- TAMBAH BUKU ---");
        System.out.print("ISBN     : "); String isbn = input.nextLine();
        System.out.print("Judul    : "); String judul = input.nextLine();
        System.out.print("Pengarang: "); String pengarang = input.nextLine();
        System.out.print("Stok     : "); String stok = input.nextLine();

        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) { System.out.println("ISBN sudah ada!"); return; }
        }
        daftarBuku.add(new String[]{isbn, judul, pengarang, stok});
        System.out.println("Buku berhasil ditambahkan!");
    }

    static void hapusBuku() {
        System.out.println("\n--- HAPUS BUKU ---");
        System.out.print("Masukkan ISBN buku: ");
        String isbn = input.nextLine();
        for (String[] p : daftarPinjam) {
            if (p[2].equals(isbn) && p[4].equals("Dipinjam")) {
                System.out.println("Buku sedang dipinjam, tidak bisa dihapus!"); return;
            }
        }
        boolean dihapus = daftarBuku.removeIf(b -> b[0].equals(isbn));
        System.out.println(dihapus ? "Buku berhasil dihapus!" : "ISBN tidak ditemukan!");
    }

    // ===== ANGGOTA =====
    static void lihatAnggota() {
        System.out.println("\n--- DAFTAR ANGGOTA ---");
        if (daftarAnggota.isEmpty()) { System.out.println("Tidak ada anggota."); return; }
        System.out.printf("%-8s %-20s %s%n", "ID", "Nama", "No. Telp");
        System.out.println("-".repeat(45));
        for (String[] a : daftarAnggota) {
            System.out.printf("%-8s %-20s %s%n", a[0], a[1], a[2]);
        }
    }

    static void tambahAnggota() {
        System.out.println("\n--- TAMBAH ANGGOTA ---");
        System.out.print("Nama    : "); String nama = input.nextLine();
        System.out.print("No Telp : "); String noTelp = input.nextLine();
        String id = "A" + String.format("%03d", daftarAnggota.size() + 1);
        daftarAnggota.add(new String[]{id, nama, noTelp});
        System.out.println("Anggota berhasil didaftarkan! ID: " + id);
    }

    static void hapusAnggota() {
        System.out.println("\n--- HAPUS ANGGOTA ---");
        System.out.print("Masukkan ID anggota: ");
        String id = input.nextLine();
        for (String[] p : daftarPinjam) {
            if (p[1].equals(id) && p[4].equals("Dipinjam")) {
                System.out.println("Anggota masih memiliki pinjaman aktif!"); return;
            }
        }
        boolean dihapus = daftarAnggota.removeIf(a -> a[0].equals(id));
        System.out.println(dihapus ? "Anggota berhasil dihapus!" : "ID tidak ditemukan!");
    }

    // ===== PEMINJAMAN =====
    static void pinjamBuku() {
        System.out.println("\n--- PINJAM BUKU ---");
        System.out.print("ID Anggota : "); String idAnggota = input.nextLine();
        System.out.print("ISBN Buku  : "); String isbn = input.nextLine();

        boolean anggotaAda = daftarAnggota.stream().anyMatch(a -> a[0].equals(idAnggota));
        if (!anggotaAda) { System.out.println("Anggota tidak ditemukan!"); return; }

        String[] buku = null;
        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) { buku = b; break; }
        }
        if (buku == null)                          { System.out.println("Buku tidak ditemukan!"); return; }
        if (Integer.parseInt(buku[3]) <= 0)        { System.out.println("Stok buku habis!"); return; }

        String idPinjam = "P" + String.format("%03d", daftarPinjam.size() + 1);
        String tgl = java.time.LocalDate.now().toString();
        daftarPinjam.add(new String[]{idPinjam, idAnggota, isbn, tgl, "Dipinjam"});
        buku[3] = String.valueOf(Integer.parseInt(buku[3]) - 1);

        System.out.println("Peminjaman berhasil! ID Pinjam: " + idPinjam);
        System.out.println("Tanggal Pinjam: " + tgl);
    }

    static void kembalikanBuku() {
        System.out.println("\n--- KEMBALIKAN BUKU ---");
        System.out.print("ID Peminjaman: ");
        String idPinjam = input.nextLine();

        for (String[] p : daftarPinjam) {
            if (p[0].equals(idPinjam)) {
                if (p[4].equals("Dikembalikan")) {
                    System.out.println("Buku sudah pernah dikembalikan!"); return;
                }
                p[4] = "Dikembalikan";
                for (String[] b : daftarBuku) {
                    if (b[0].equals(p[2])) {
                        b[3] = String.valueOf(Integer.parseInt(b[3]) + 1); break;
                    }
                }
                System.out.println("Buku berhasil dikembalikan!");
                System.out.println("Tanggal Kembali: " + java.time.LocalDate.now());
                return;
            }
        }
        System.out.println("ID Peminjaman tidak ditemukan!");
    }

    static void lihatPeminjaman() {
        System.out.println("\n--- DATA PEMINJAMAN ---");
        if (daftarPinjam.isEmpty()) { System.out.println("Belum ada data peminjaman."); return; }
        System.out.printf("%-8s %-10s %-8s %-14s %s%n", "ID", "ID Anggota", "ISBN", "Tgl Pinjam", "Status");
        System.out.println("-".repeat(55));
        for (String[] p : daftarPinjam) {
            System.out.printf("%-8s %-10s %-8s %-14s %s%n", p[0], p[1], p[2], p[3], p[4]);
        }
    }
}