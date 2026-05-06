import java.util.*;

public class Perpustakaan {

    // ==== DATA SIMPANAN ====
    static List<String[]> daftarBuku = new ArrayList<>();
    static List<String[]> daftarAnggota = new ArrayList<>();
    static List<String[]> daftarPinjam = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // Data awal buku (ISBN, Judul, Pengarang, Stok)
        daftarBuku.add(new String[] { "001", "Laskar Pelangi", "Andrea Hirata", "10" });
        daftarBuku.add(new String[] { "002", "Sang Pemimpi", "Andrea Hirata", "8" });
        daftarBuku.add(new String[] { "003", "Edensor", "Andrea Hirata", "5" });
        daftarBuku.add(new String[] { "004", "Maryamah Karpov", "Andrea Hirata", "7" });
        daftarBuku.add(new String[] { "005", "Negeri 5 Menara", "Ahmad Fuadi", "6" });
        daftarBuku.add(new String[] { "006", "Ayat-Ayat Cinta", "H. El Shirazy", "9" });
        daftarBuku.add(new String[] { "007", "Perahu Kertas", "Dee Lestari", "4" });
        daftarBuku.add(new String[] { "008", "Supernova", "Dee Lestari", "3" });
        daftarBuku.add(new String[] { "009", "Filosofi Teras", "Henry Manampiring", "12" });
        daftarBuku.add(new String[] { "010", "Bumi", "Tere Liye", "11" });
        daftarBuku.add(new String[] { "011", "Bulan", "Tere Liye", "10" });
        daftarBuku.add(new String[] { "012", "Matahari", "Tere Liye", "8" });
        daftarBuku.add(new String[] { "013", "Hujan", "Tere Liye", "6" });
        daftarBuku.add(new String[] { "014", "Rindu", "Tere Liye", "5" });
        daftarBuku.add(new String[] { "015", "Pulang", "Tere Liye", "4" });
        daftarBuku.add(new String[] { "016", "Dilan 1990", "Pidi Baiq", "3" });
        daftarBuku.add(new String[] { "017", "Dilan 1991", "Pidi Baiq", "7" });
        daftarBuku.add(new String[] { "018", "Milea", "Pidi Baiq", "6" });
        daftarBuku.add(new String[] { "019", "Koala Kumal", "Raditya Dika", "5" });
        daftarBuku.add(new String[] { "020", "Kambing Jantan", "Raditya Dika", "4" });
        daftarBuku.add(new String[] { "021", "Cinta Brontosaurus", "Raditya Dika", "6" });
        daftarBuku.add(new String[] { "022", "Manusia Setengah Salmon", "Raditya Dika", "7" });
        daftarBuku.add(new String[] { "023", "Ketika Cinta Bertasbih", "H. El Shirazy", "9" });
        daftarBuku.add(new String[] { "024", "Bumi Cinta", "H. El Shirazy", "8" });
        daftarBuku.add(new String[] { "025", "Dalam Mihrab Cinta", "H. El Shirazy", "7" });
        daftarBuku.add(new String[] { "026", "5 cm", "Donny Dhirgantoro", "5" });
        daftarBuku.add(new String[] { "027", "Sunshine Becomes You", "Ilana Tan", "6" });
        daftarBuku.add(new String[] { "028", "Autumn in Paris", "Ilana Tan", "10" });
        daftarBuku.add(new String[] { "029", "Summer in Seoul", "Ilana Tan", "9" });
        daftarBuku.add(new String[] { "030", "Winter in Tokyo", "Ilana Tan", "8" });
        daftarBuku.add(new String[] { "031", "Ranah 3 Warna", "Ahmad Fuadi", "7" });
        daftarBuku.add(new String[] { "032", "Rantau 1 Muara", "Ahmad Fuadi", "6" });
        daftarBuku.add(new String[] { "033", "Gajah Mada", "Langit Kresna H", "5" });
        daftarBuku.add(new String[] { "034", "Pramoedya Bumi Manusia", "Pramoedya A T", "4" });
        daftarBuku.add(new String[] { "035", "Anak Semua Bangsa", "Pramoedya A T", "3" });
        daftarBuku.add(new String[] { "036", "Jejak Langkah", "Pramoedya A T", "2" });
        daftarBuku.add(new String[] { "037", "Rumah Kaca", "Pramoedya A T", "3" });
        daftarBuku.add(new String[] { "039", "Di Bawah Lindungan Kabah", "Hamka", "5" });
        daftarBuku.add(new String[] { "040", "Sitti Nurbaya", "Marah Rusli", "6" });
        daftarBuku.add(new String[] { "041", "Atheis", "Achdiat K. Mihardja", "7" });
        daftarBuku.add(new String[] { "042", "Ronggeng Dukuh Paruk", "Ahmad Tohari", "8" });
        daftarBuku.add(new String[] { "043", "Cantik Itu Luka", "Eka Kurniawan", "9" });
        daftarBuku.add(new String[] { "044", "Lelaki Harimau", "Eka Kurniawan", "6" });
        daftarBuku.add(new String[] { "045", "O", "Eka Kurniawan", "5" });
        daftarBuku.add(new String[] { "047", "Gadis Kretek", "Ratih Kumala", "3" });
        daftarBuku.add(new String[] { "048", "Orang-Orang Biasa", "Andrea Hirata", "6" });
        daftarBuku.add(new String[] { "049", "Sirkus Pohon", "Andrea Hirata", "7" });
        daftarBuku.add(new String[] { "050", "Laskar Cinta", "H. El Shirazy", "8" });
        daftarBuku.add(new String[] { "051", "Haji Backpacker", "Aguk Irawan MN", "9" });
        daftarBuku.add(new String[] { "052", "Negeri di Ujung Tanduk", "Tere Liye", "10" });
        daftarBuku.add(new String[] { "053", "Negeri Para Bedebah", "Tere Liye", "6" });
        daftarBuku.add(new String[] { "054", "Sunset Bersama Rosie", "Tere Liye", "7" });
        daftarBuku.add(new String[] { "055", "Hafalan Shalat Delisa", "Tere Liye", "5" });
        daftarBuku.add(new String[] { "056", "Si Anak Cahaya", "Tere Liye", "8" });
        daftarBuku.add(new String[] { "057", "Si Anak Badai", "Tere Liye", "6" });
        daftarBuku.add(new String[] { "058", "Si Anak Pintar", "Tere Liye", "4" });
        daftarBuku.add(new String[] { "059", "Bintang", "Tere Liye", "3" });
        daftarBuku.add(new String[] { "060", "Komet", "Tere Liye", "5" });

        // data awal anggota (ID, Nama, No. Telp) 60baris
        ArrayList<String[]> daftarAnggota = new ArrayList<>();

        daftarAnggota.add(new String[] { "A001", "Irfan Hakim", "085283557671" });
        daftarAnggota.add(new String[] { "A002", "Rizky Ramadhan", "081234567890" });
        daftarAnggota.add(new String[] { "A003", "Dinda Putri", "082345678901" });
        daftarAnggota.add(new String[] { "A004", "Fajar Nugroho", "083456789012" });
        daftarAnggota.add(new String[] { "A005", "Siti Aisyah", "084567890123" });
        daftarAnggota.add(new String[] { "A006", "Agus Setiawan", "085678901234" });
        daftarAnggota.add(new String[] { "A007", "Rina Marlina", "086789012345" });
        daftarAnggota.add(new String[] { "A008", "Budi Santoso", "087890123456" });
        daftarAnggota.add(new String[] { "A009", "Nadia Putri", "088901234567" });
        daftarAnggota.add(new String[] { "A010", "Andi Saputra", "089012345678" });

        daftarAnggota.add(new String[] { "A011", "Dewi Lestari", "081112223334" });
        daftarAnggota.add(new String[] { "A012", "Yoga Pratama", "082223334445" });
        daftarAnggota.add(new String[] { "A013", "Maya Sari", "083334445556" });
        daftarAnggota.add(new String[] { "A014", "Rudi Hartono", "084445556667" });
        daftarAnggota.add(new String[] { "A015", "Intan Permata", "085556667778" });
        daftarAnggota.add(new String[] { "A016", "Hendra Wijaya", "086667778889" });
        daftarAnggota.add(new String[] { "A017", "Putra Mahendra", "087778889990" });
        daftarAnggota.add(new String[] { "A018", "Citra Dewi", "088889990001" });
        daftarAnggota.add(new String[] { "A019", "Galih Prakoso", "089990001112" });
        daftarAnggota.add(new String[] { "A020", "Tania Putri", "081000111222" });

        daftarAnggota.add(new String[] { "A021", "Wahyu Hidayat", "082111222333" });
        daftarAnggota.add(new String[] { "A022", "Nina Kurnia", "083222333444" });
        daftarAnggota.add(new String[] { "A023", "Eko Prasetyo", "084333444555" });
        daftarAnggota.add(new String[] { "A024", "Lina Handayani", "085444555666" });
        daftarAnggota.add(new String[] { "A025", "Arif Rahman", "086555666777" });
        daftarAnggota.add(new String[] { "A026", "Salsa Putri", "087666777888" });
        daftarAnggota.add(new String[] { "A027", "Bayu Saputra", "088777888999" });
        daftarAnggota.add(new String[] { "A028", "Rizka Amelia", "089888999000" });
        daftarAnggota.add(new String[] { "A029", "Doni Kurniawan", "081999000111" });
        daftarAnggota.add(new String[] { "A030", "Fitriani", "082000111222" });

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
                            case 3 -> hapusBuku();
                            case 4 -> lihatAnggota();
                            case 5 -> tambahAnggota();
                            case 6 -> hapusAnggota();
                            case 7 -> lihatPeminjaman();
                            case 8 -> menuSorting();
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
        System.out.println(" 3.  Hapus Buku");
        System.out.println(" 4.  Lihat Daftar Anggota");
        System.out.println(" 5.  Tambah Anggota");
        System.out.println(" 6.  Hapus Anggota");
        System.out.println(" 7.  Lihat Data Peminjaman");
        System.out.println(" 8. Urutkan Data (Sorting)");
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

    // ===== BUKU =====
    static void lihatBuku() {

        System.out.println("\n--- DAFTAR BUKU ---");

        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku.");
            return;
        }

        System.out.printf("%-6s %-25s %-20s %s%n", "ISBN", "Judul", "Pengarang", "Stok");

        // repeat() hanya tersedia di Java 11 ke atas, jika menggunakan versi lebih lama
        // bisa pakai loop untuk cetak garis
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
        for (String[] b : daftarBuku) {
            System.out.printf("%-6s %-25s %-20s %s%n", b[0], b[1], b[2], b[3]);
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
        System.out.print("Pengarang: ");
        String pengarang = input.nextLine();
        System.out.print("Stok     : ");
        String stok = input.nextLine();

        for (String[] b : daftarBuku) {
            if (b[0].equals(isbn)) {
                System.out.println("ISBN sudah ada!");
                return;
            }
        }
        daftarBuku.add(new String[] { isbn, judul, pengarang, stok });
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
        System.out.print("Nama    : ");
        String nama = input.nextLine();
        System.out.print("No Telp : ");
        String noTelp = input.nextLine();
        String id = "A" + String.format("%03d", daftarAnggota.size() + 1);
        daftarAnggota.add(new String[] { id, nama, noTelp });
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
        if (Integer.parseInt(buku[3]) <= 0) {
            System.out.println("Stok buku habis!");
            return;
        }

        String idPinjam = "P" + String.format("%03d", daftarPinjam.size() + 1);
        String tgl = java.time.LocalDate.now().toString();
        daftarPinjam.add(new String[] { idPinjam, idAnggota, isbn, tgl, "Dipinjam" });
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
        System.out.printf("%-8s %-10s %-8s %-14s %s%n", "ID", "ID Anggota", "ISBN", "Tgl Pinjam", "Status");
        System.out.println("-".repeat(55));
        for (String[] p : daftarPinjam) {
            System.out.printf("%-8s %-10s %-8s %-14s %s%n", p[0], p[1], p[2], p[3], p[4]);
        }
    }
}