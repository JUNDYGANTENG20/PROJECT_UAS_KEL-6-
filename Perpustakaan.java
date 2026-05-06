import java.util.*;

public class Perpustakaan {

    // ===== DATA STORAGE =====
    static List<String[]> daftarBuku     = new ArrayList<>();
    static List<String[]> daftarAnggota  = new ArrayList<>();
    static List<String[]> daftarPinjam   = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // Format Buku    : [isbn, judul, pengarang, stok]
    // Format Anggota : [id, nama, noTelp]
    // Format Pinjam  : [idPinjam, idAnggota, isbn, tglPinjam, status]

    public static void main(String[] args) {
        // Data awal
        daftarBuku.add(new String[]{"001", "Laskar Pelangi", "Andrea Hirata", "3"});
        daftarBuku.add(new String[]{"002", "Bumi Manusia", "Pramoedya Ananta", "2"});
        daftarBuku.add(new String[]{"003", "Harry Potter", "J.K. Rowling", "5"});

        daftarAnggota.add(new String[]{"A001", "Budi Santoso", "08123456789"});
        daftarAnggota.add(new String[]{"A002", "Siti Rahayu", "08987654321"});

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = Integer.parseInt(input.nextLine());
            switch (pilihan) {
                case 1 -> lihatBuku();
                case 2 -> tambahBuku();
                case 3 -> hapusBuku();
                case 4 -> lihatAnggota();
                case 5 -> tambahAnggota();
                case 6 -> hapusAnggota();
                case 7 -> pinjamBuku();
                case 8 -> kembalikanBuku();
                case 9 -> lihatPeminjaman();
                case 0 -> System.out.println("Terima kasih. Program selesai.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }

    // ===== MENU =====
    static void tampilkanMenu() {
        System.out.println("\n====================================");
        System.out.println("     SISTEM PERPUSTAKAAN");
        System.out.println("====================================");
        System.out.println(" 1. Lihat Daftar Buku");
        System.out.println(" 2. Tambah Buku");
        System.out.println(" 3. Hapus Buku");
        System.out.println(" 4. Lihat Daftar Anggota");
        System.out.println(" 5. Tambah Anggota");
        System.out.println(" 6. Hapus Anggota");
        System.out.println(" 7. Pinjam Buku");
        System.out.println(" 8. Kembalikan Buku");
        System.out.println(" 9. Lihat Data Peminjaman");
        System.out.println(" 0. Keluar");
        System.out.println("====================================");
        System.out.print("Pilih menu: ");
    }

    // ===== BUKU =====
    static void lihatBuku() {
        System.out.println("\n--- DAFTAR BUKU ---");
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku.");
            return;
        }
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
            if (b[0].equals(isbn)) {
                System.out.println("ISBN sudah ada!");
                return;
            }
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
                System.out.println("Buku sedang dipinjam, tidak bisa dihapus!");
                return;
            }
        }
        boolean dihapus = daftarBuku.removeIf(b -> b[0].equals(isbn));
        System.out.println(dihapus ? "Buku berhasil dihapus!" : "ISBN tidak ditemukan!");
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
                System.out.println("Anggota masih memiliki pinjaman aktif!");
                return;
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

        // Cek anggota
        boolean anggotaAda = daftarAnggota.stream().anyMatch(a -> a[0].equals(idAnggota));
        if (!anggotaAda) { System.out.println("Anggota tidak ditemukan!"); return; }

        // Cek buku & stok
        String[] buku = null;
        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) { buku = b; break; }
        }
        if (buku == null) { System.out.println("Buku tidak ditemukan!"); return; }
        if (Integer.parseInt(buku[3]) <= 0) { System.out.println("Stok buku habis!"); return; }

        // Simpan peminjaman
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
                    System.out.println("Buku sudah pernah dikembalikan!");
                    return;
                }
                p[4] = "Dikembalikan";
                // Tambah stok kembali
                for (String[] b : daftarBuku) {
                    if (b[0].equals(p[2])) {
                        b[3] = String.valueOf(Integer.parseInt(b[3]) + 1);
                        break;
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
        if (daftarPinjam.isEmpty()) {
            System.out.println("Belum ada data peminjaman.");
            return;
        }
        System.out.printf("%-8s %-10s %-8s %-14s %s%n",
                "ID", "ID Anggota", "ISBN", "Tgl Pinjam", "Status");
        System.out.println("-".repeat(55));
        for (String[] p : daftarPinjam) {
            System.out.printf("%-8s %-10s %-8s %-14s %s%n",
                    p[0], p[1], p[2], p[3], p[4]);
        }
    }
}