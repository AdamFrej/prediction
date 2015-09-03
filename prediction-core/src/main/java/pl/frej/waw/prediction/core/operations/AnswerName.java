package pl.frej.waw.prediction.core.operations;


public class AnswerName {
    private String seed="";
    private long iterator=0;

    public AnswerName(String seed) {
        this.seed = seed;
    }

    public String next(){
        iterator++;
        modifySeed();
        return String.format("%s%d", seed, iterator);
    }

    private void modifySeed() {
        seed.toLowerCase().trim();
    }
}
