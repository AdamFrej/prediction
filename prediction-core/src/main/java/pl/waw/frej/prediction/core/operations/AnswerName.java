package pl.waw.frej.prediction.core.operations;


public class AnswerName {
    private String seed="";
    private long iterator=0;

    public AnswerName(String seed) {
        this.seed = seed;
        modifySeed();
    }

    public String next(){
        iterator++;
        return String.format("%s%d", seed, iterator);
    }

    private void modifySeed() {
        toCamelCase();
        seed = seed.replaceAll("[^a-zA-Z]", "");
    }

    private void toCamelCase() {
        final StringBuilder ret = new StringBuilder(seed.length());

        for (final String word : seed.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(Character.toUpperCase(word.charAt(0)));
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length()==seed.length()))
                ret.append(" ");
        }

        seed = ret.toString();
    }
}
