import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Playlist — ADT แทนรายการเพลงที่ผู้ใช้จัดลำดับไว้
 *
 * ค่านามธรรม (A): ลำดับของเพลง เช่น [เพลงA, เพลงB, เพลงC]
 *
 * ตัวอย่างการใช้งาน:
 *     Playlist p = new Playlist();
 *     p.add("Bohemian Rhapsody");
 *     p.add("Imagine");
 *     System.out.println(p.size());   // 2
 */
public class Playlist {

    public static final int MAX_SONGS = 100;

    // ===== representation =====
    private final List<String> songs;

    // TODO 1: เขียน Abstraction Function ตรงนี้
    // Abstraction Function:
    //   AF(songs) =af(songs) = เพลลิสตืที่ประกอบไปด้วย
    // เพลงตามลำดับในเพลลิสต์

    // TODO 2: เขียน Representation Invariant ตรงนี้ (4 ข้อ)
    // Representation Invariant:
    //  RI = songs != null
    // สมาชิคต้องไม่เป็น null
    //ชื่อเพลงต้องไม่เปนสติงว่าง
    //ชื่อเพลงต้องไม่ซ้ำกัน
    //เพลง ต้องอยู่ละหว่าง 0 ถึง max
    
        

    // TODO 3: เขียน Safety from rep exposure ตรงนี้
    // Safety from rep exposure:
    //songs ต้องเป็นprivate final
    //ต้องบันทึกขาเข้าขาออก

    /**
     * TODO 4: เขียน checkRep()
     * แปลง RI ทุกข้อเป็น assert หนึ่งบรรทัด พร้อมข้อความอธิบาย
     */
    private void checkRep() {
        assert songs != null : "เพลงต้องไม่เป็นnull";
        assert songs.size() <= MAX_SONGS : "จำนวนเพลงเกินจำนวนที่กำหนด"; 
       
        Set<String> seen = new HashSet<>();
        for (String s : songs) {
            assert s != null : "ชื่อเพลงต้องไม่เป็นnull";
            assert !s.trim().isEmpty() : "ชื่อเพลงต้องไม่เป็นสตริงว่าง";
            assert seen.add(s) : "ชื่อเพลงต้องไม่ซ้ำ";
        }
    }

    // ===== Creator =====

    /**
     * สร้างเพลย์ลิสต์ว่าง
     */
    public Playlist() {
        this.songs = new ArrayList<>();
        checkRep();
    }

    /**
     * TODO 5: Creator ตัวที่สอง
     * สร้างเพลย์ลิสต์จากรายชื่อเพลงที่ให้มา
     *
     * ระวัง: ห้ามเก็บ reference ของ initial ตรง ๆ (rep exposure!)
     *
     * @param initial รายชื่อเพลงเริ่มต้น ต้องไม่ซ้ำและไม่เกิน MAX_SONGS
     * @throws IllegalArgumentException ถ้า initial ผิดเงื่อนไข
     */
    public Playlist(List<String> initial) {
        if (initial == null) throw new IllegalArgumentException("initial ต้องไม่เป็น null");
        if (initial.size() > MAX_SONGS) throw new IllegalArgumentException("จำนวนเพลงเกินจำนวนที่กำหนด");
        Set<String> seen = new HashSet<>();
        for (String s : initial) {
            if(s==null) throw new IllegalArgumentException("ชื่อเพลงต้องไม่เป็น null");
            if(s.trim().isEmpty()) throw new IllegalArgumentException("ชื่อเพลงต้องไม่เป็นสตริงว่าง");
            if(!seen.add(s)) throw new IllegalArgumentException("ชื่อเพลงต้องไม่ซ้ำ");
        }

        // เมื่อผ่าน Validation แล้วค่อยสร้าง Copy
        this.songs = new ArrayList<>(initial);
        checkRep();
    }
   
    // ===== Mutators =====

    /**
     * TODO 6: เพิ่มเพลงต่อท้ายเพลย์ลิสต์
     *
     * @param song ชื่อเพลง ต้องไม่เป็น null และไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีเพลงนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า song เป็น null หรือสตริงว่าง
     */
    public boolean add(String song) {
        if (song == null || song.isEmpty()) 
            throw new IllegalArgumentException("song ต้องไม่เป็น null หรือสตริงว่าง");
        
        
        if (songs.contains(song) || songs.size() >= MAX_SONGS) return false;
        
        songs.add(song);
        checkRep();
        return true;
    }
    /**
     * TODO 7: ลบเพลงออกจากเพลย์ลิสต์
     *
     * @param song ชื่อเพลงที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบเพลงนี้
     */
    public boolean remove(String song) {
        if(!songs.contains(song)) return false;
        songs.remove(song);
        checkRep();
        return true;
    }

    // ===== Observers =====

    /**
     * TODO 8: คืนจำนวนเพลงในเพลย์ลิสต์
     */
    public int size() {
        return songs.size();
    }

    /**
     * TODO 9: ตรวจว่ามีเพลงนี้อยู่หรือไม่
     */
    public boolean contains(String song) {
        if (song == null) {
            throw new IllegalArgumentException("song ต้องไม่เป็น null");
        }
        if (song.isEmpty()) {
            throw new IllegalArgumentException("song ต้องไม่เป็นสตริงว่าง");
        }
        return songs.contains(song);
    }

    /**
     * TODO 10: คืนรายชื่อเพลงทั้งหมดตามลำดับ
     *
     * ระวัง: ห้ามคืน reference ของ songs ตรง ๆ (rep exposure!)
     */
    public List<String> songs() {
        return new ArrayList<>(songs);
    }

    // ===== Producer =====

    /**
     * TODO 11: คืนเพลย์ลิสต์ใหม่ที่มีเพลงเดียวกันแต่สลับลำดับ
     *
     * ระวัง: ห้ามแก้เพลย์ลิสต์เดิม (this) เด็ดขาด
     *
     * @return เพลย์ลิสต์ใหม่ที่สลับลำดับแล้ว
     */
    public Playlist shuffled() {
        List<String> copy = new ArrayList<>(songs);
        java.util.Collections.shuffle(copy);
        return new Playlist(copy);
    }

    @Override
    public String toString() {
        return songs.toString();
    }
}
