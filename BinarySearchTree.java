class BST {
    public int data;    // 要素の値
    public int left;    // 左側の要素にたどるポインタ
    public int right;   // 右側の要素にたどるポインタ
}

// 二分探索木を操作するクラスの定義
public class BinarySearchTree {
    // 二分探索木の実体となる配列（要素数を最大10個とする）
    public static BST[] tree = new BST[10];

    // 根の要素の添え字（連結リストの先頭ポインタ）
    public static int rootIdx = 0;

    // 次に格納する要素の添え字
    public static int newIdx = 0;

    // 二分探索木に要素を追加するメソッド
    public static void addBST(int data) {
        int currentIdx;     // 現在たどっている要素の添え字
        boolean addFlag;    // 追加が完了したことを示すフラグ

        // 物理的な位置に追加する
        tree[newIdx].data = data;
        tree[newIdx].left = -1;
        tree[newIdx].right = -1;

        // 根のデータでないなら、論理的な位置にポインタを設定する
        if (newIdx != rootIdx) {
            currentIdx = rootIdx;   // 根から二分探索木をたどる
            addFlag = false;        // 追加は完了していない

            do {
                // より小さい場合は、左側にたどる
                if (data < tree[currentIdx].data) {
                    // 左側が末端なら、そこに追加する
                    if (tree[currentIdx].left == -1) {
                        tree[currentIdx].left = newIdx;
                        addFlag = true;
                    }
                    // 左側が末端でないなら、さらに左側の要素をたどる
                    else {
                        currentIdx = tree[currentIdx].left;
                    }
                }
                // より大きい場合は、右側にたどる（同じ値はないとする）
                else {
                    // 右側が末端なら、そこに追加する
                    if (tree[currentIdx].right == -1) {
                        tree[currentIdx].right = newIdx;
                        addFlag = true;
                    }
                    // 右側が末端でないなら、さらに右側をたどる
                    else {
                        currentIdx = tree[currentIdx].right;
                    }
                }
            } while (addFlag == false);
        }
        // 次に格納する要素のために添え字を1増やしておく
        newIdx++;
    }

    //　二分探索木の実体の配列を、物理的な順序で表示するメソッド
    public static void printPhysicalBST() {
        int i;

        for (i = 0; i < newIdx; i++) {
            System.out.printf("tree[%d]:data = %d, left = %d, right = %d\n",i, tree[i].data, tree[i].left, tree[i].right);
        }
    }

    // 二分探索木を論理的な順序（深さ優先探索）で表示するメソッド
    public static void printLogicalBST(int currentIdx) {
        if (currentIdx != -1) {
            System.out.printf("tree[%d]:data = %d, left = %d, right = %d\n",currentIdx, tree[currentIdx].data, tree[currentIdx].left, tree[currentIdx].right);

            // この部分が再帰呼び出し
            printLogicalBST(tree[currentIdx].left);
            printLogicalBST(tree[currentIdx].right);
        }
    }

    // プログラムの実行開始位置となるmainメソッド
    public static void main(String[] args) {
        // javaではインスタンスの生成が必要
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new BST();
        }

        // 二分探索木を構築して、物理的な順序で表示する
        addBST(4);
        addBST(6);
        addBST(5);
        addBST(2);
        addBST(3);
        addBST(7);
        addBST(1);
        printPhysicalBST();

        // 二分探索木を論理的な順序（深さ優先探索）で表示する
        System.out.printf("----------------------------------------\n");
        printLogicalBST(rootIdx);
    }
}