package Leetcode;

import java.util.Stack;

public class LC20 {
    // 对称括号问题 -> {[()]}/()[]{}
    // 括号完全对称，或对应括号相邻，且匹配完不会有剩余元素。即，每一次pop都能有右侧括号与之对应。
    public static boolean isValid(String s) {
        // 1. 建立一个栈用于存放左括号
        Stack<Character> stack = new Stack<>();
        // 2. 将String打散为Char,依次遍历
        for (int i = 0; i < s.length(); i++) {
            // 3. 每次读取一个char
            char c = s.charAt(i);
            // 4. 如果是左侧括号，无论顺序全部入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 5-1. 如果栈空了，即没有右侧括号也没东西了，判断False
                if (stack.isEmpty()) {
                    return false;
                }
                // 5-2. 如果有，则拿出当前栈顶的元素和它比较
                char topChar = stack.pop();
                // 5-3: 若左右不匹配，则false
                // c是string中当前读取char，topChar是当前栈顶的元素
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        // 6. 如果全部pop完，栈空，且全部匹配正确，则true；若匹配结束但栈还有东西，则false
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{asd}}[qq";
        String s1 = "{[()]}";
        System.out.println(isValid(s));
        System.out.println(isValid(s1));
    }
}
