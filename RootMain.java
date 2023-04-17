public class RootMain {
    public static void main(String[] args) {
        String input = "4 + 5 * 2 - 3";
        // String input = "4 + 5 + 2 - 3"; //-++4523
        RootTree rt = new RootTree(input);
        rt.findPrefix();
        rt.findPostFix();
        rt.findRootTree();
    }
}
