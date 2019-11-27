package cn.faceland.springbootalgorithm.leetcode;

/**
 * @author watermelon
 * @Date 2019-11-25
 */
public class TwoSum2 extends AbstractHandler{

    /**
     给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

     示例：

     输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出：7 -> 0 -> 8
     原因：342 + 465 = 807

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/add-two-numbers
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */



    @Override
    void doCalculations() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode a = l1;
        while (a!= null){
            System.out.println(a.val);

            a = a.next;
        }

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode b = l2;
        while (b!= null){
            System.out.println(b.val);

            b = b.next;
        }

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode!= null){
            System.out.println(listNode.val);

            listNode = listNode.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        int carry = 0;
        ListNode p = l1, q = l2, curr = result;
        int x,y,sum;
        while(p != null || q != null){
            x = p != null ? p.val : 0;
            y = q != null ? q.val : 0;
            System.out.println("x:" + x + ";y:" + y );
            sum = x + y + carry;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            carry = sum / 10;
            if(carry > 0){
                curr.next = new ListNode(carry);
            }

            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }
        return result.next;
    }
}
