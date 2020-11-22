public class Image {
    private String filename;
    private String outPath;

    Image(String filename, String outPath) {
        this.filename = filename;
        this.outPath = outPath;
    }

    /**
    * setter
    *  */
    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * getter
     *  */
    public String getFilename() {
        return filename;
    }

    public String getOutPath() {
        return outPath;
    }
}
