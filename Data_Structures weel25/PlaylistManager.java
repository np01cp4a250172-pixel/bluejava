import java.util.LinkedList;


public class PlaylistManager
{
    public static void main(String[] args) {
        LinkedList<String> playlist = new LinkedList<>();

        // Add three songs
        playlist.add("Bohemian Rhapsody");
        playlist.add("Blinding Lights");
        playlist.add("Imagine");

        // Add to beginning and end
        playlist.addFirst("Billie Jean");
        playlist.addLast("Rolling in the Deep");

        // Remove the second song (index 1)
        playlist.remove(1);//bohemian rhapsody

        // Print final playlist
        System.out.println("Final Playlist:");
        for (String song : playlist) {
            System.out.println("- " + song);
        }
    }
}