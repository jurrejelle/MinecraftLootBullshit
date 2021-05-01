package seedFinding.seedFindingProject;

public class main {


    public static void main (String[] args) {
        int TOTAL_THREADS = 10;

        for(int offset = 0; offset< TOTAL_THREADS; offset++) {
            Runnable myThread = new diamondThread(offset, TOTAL_THREADS);
            new Thread(myThread).start();
        }

    }

}
