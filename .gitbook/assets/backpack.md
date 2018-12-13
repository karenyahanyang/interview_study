# Backpack

@(Data Structure & Algorithm)

[Backpack - LintCode](https://www.lintcode.com/ladder/48/)

## 1. What are backpack problems?

简单来讲，你有一个背包，它的容量为 $V$，你同时有若干个物品，它们有各自的**体积**和**价值**，将哪些物品装入背包可以使得它们的总体积和不超过背包的容量而总价值和最大？

我们把形如这样的一类与背包有关的问题称为背包问题。

### 1.1 Types of Backpack Problems
1. 0-1 背包问题 Backpack, Backpack II, Backpack V, Backpack VI
2. 多重背包问题 Backpack VII, Backpack VIII
3. 完全背包问题 Backpack III, Backpack IV

## 2. 0-1 Backpack Problems

**问题概述**
> 有 $N$ 种物品，一个容量为 $V$ 的背包，第 i 件物品的体积为 $cap[i]$，价值为 $val[i]$，求在不超过背包容量限制的情况下所能获得的**最大物品价值和**为多少？

因为在这个问题中，一件物品要么不选，要么选，正好对应于 `0-1` 两个状态，所以我们一般把形如这样的背包问题称作 0-1 背包问题。

**问题分析**
对于 0-1 背包问题，我们一般选择利用动态规划进行求解。

状态：
$backpack[i][j]$ 代表在前 $i$件物品中选择若干件，这若干件物品的体积和不超过 $j$ 的情况下，所能获得的最大物品价值和。

状态转移方程：
- $backpack[i][j] = max(backpack[i - 1][j], backpack[i - 1][j - cap[i]] + val[i])$
如果不将第 $i$ 件物品放入背包，那么 $backpack[i][j]$的值为 $backpack[i - 1][j]$；如果将第 $i$ 件物品放入背包，那么 $backpack[i][j] = backpack[i - 1][j - cap[i]] + val[i]$。我们根据 $backpack[i - 1][j]$和 $backpack[i - 1][j - cap[i]] + val[i]$ 的大小选择放还是不放第 $i$ 件物品。
- $backpack[0][j] = 0, j \in [0, V]$
在前 $0$ 件物品中选择若干件，也就是一件都不选，此时无论物品的体积和为何值，物品价值和均为 $0$。
那么最后的答案为 $backpack[N][V]$，即在前 $N$ 件物品中选择若干件，这若干件物品的体积和不超过 $V$ 的情况下，所能获得的最大物品价值和。

根据以上思路可以得到 Core Code。

Java 代码如下：
```java
for (int i = 1; i <= N; ++i) {
    for (int j = 0; j <= V; ++j) {
        backpack[i][j] = backpack[i - 1][j];
        if (j >= cap[i]) {
            backpack[i][j] = Math.max(backpack[i][j], backpack[i - 1][j - cap[i]] + val[i]);
        }
    }
}
```
时间复杂度 $O(NV)$，空间复杂度 $O(NV)$。

### [125. Backpack II - LintCode](https://www.lintcode.com/problem/backpack-ii/description)

> 给出 n 个物品的体积 A[i] 和其价值 V[i] ，将他们装入一个大小为 m 的背包，最多能装入的总价值有多大？

注意事项：A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的 m。

可以发现这是一个“裸”的 0-1 背包问题，其中 n 相当于 $N$，m 相当于 $V$，A[i] 相当于 $cap[i]$，V[i] 相当于 $val[i]$，根据上面的模板可以得到代码。

Java 代码如下：
```java
public class Solution {
    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[][] backpack = new int[n + 1][m + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= m; ++j) {
                backpack[i + 1][j] = backpack[i][j];
                if (j >= A[i]) {
                    backpack[i + 1][j] = Math.max(backpack[i + 1][j], backpack[i][j - A[i]] + V[i]);
                }
            }
        }
        return backpack[n][m];
    }
}
```
**Optimization with Rolling Array**
目前我们得到了一个空间复杂度为 $O(NV)$ 的算法来解决 0-1 背包问题。

观察到在计算 $backpack[i][j]$ 时，我们只用到了 $backpack[i - 1][j]$ 和 $backpack[i - 1][j - cap[i]]$这两个数，并没有用到$backpack[i - 1][], backpack[i - 2][], \dots$的信息，也就是 $backpack[i][]$ 只和 $backpack[i - 1][]$ 有关，那么我们只需要两个数组就够了。
```java
for (int i = 1; i <= N; ++i) {
    for (int j = 0; j <= V; ++j) {
        int index = i & 1;
        backpack[index][j] = backpack[index ^ 1][j];
        if (j >= cap[i]) {
            backpack[index][j] = Math.max(backpack[index][j], backpack[index ^ 1][j - cap[i]] + val[i]);
        }
    }
}
```
空间复杂度从 $O(NV)$ 降到了 $O(V)$。

**一维数组优化**
虽然空间复杂度从 $O(NV)$ 降到了 $O(V)$，但是我们仍然需要 $backpack[2][V]$ 这样的二维数组。我们只需要把第二层循环倒序即可。

```java
for (int i = 1; i <= N; ++i) {
    for (int j = V; j >= cap[i]; --j) {
        f[j] = Math.max(f[j], f[j - cap[i]] + val[i]);
    }
}
```
在第 $i$ 层循环初 $f[j]$ 存的相当于 $backpack[i - 1][j]$的值。

在更新 $f[j]$ 时，我们用到了 $f[j - cap[i]]$，由于第二层循环倒序，所以 $f[j - cap[i]]$ 未被更新，此时它代表 $backpack[i - 1][j - cap[i]]$。所以 $f[j] = Math.max(f[j], f[j - cap[i]] + val[i]) $等价于 $backpack[i][j] = Math.max(backpack[i - 1][j], backpack[i - 1][j - cap[i]] + val[i])$。

在第 $i$ 层循环末 $f[j]$ 存的相当于$backpack[i][j]$的值。
```java
public class Solution {
    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[] f = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = m; j >= A[i]; --j) {
                f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
            }
        }
        return f[m];
    }
}
```

### [92. Backpack - LintCode](https://www.lintcode.com/problem/backpack/description)

> 在 $n$ 个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为 $m$，每个物品的大小为 $A[i]$

这个问题没有给每个物品的价值，也没有问最多能获得多少价值，而是问最多能装多满。
实际上在这里，如果我们**把每个物品的大小当作每个物品的价值**，就可以完美解决这个问题。
```java
public class Solution {
    public int backPack(int m, int[] A) {
        int n = A.length;
        int[] f = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = m; j >= A[i]; --j) {
                f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
            }
        }
        return f[m];
    }
}
```
## 3. 多重背包问题

**问题概述**
除了 0-1 背包问题外，还有一种背包问题称作`多重背包问题`。

>有$N$种物品，一个容量为$V$的背包，第$i$种物品的`数量为$num[i]$`，体积为$cap[i]$，价值为$val[i]$，求在不超过背包的容量限制的情况下所能获得的最大物品价值和为多少？

这个问题与 0-1 背包的区别在于，0-1 背包中每种物品有且只有一个，而**多重背包中一种物品有 $nums[i]$ 个**。我们一般把形如这样的背包问题称作多重背包问题。

**问题分析**
求解这个问题一个简单的思路就是可以把它看成一个拥有$ \sum num[i]$件物品的 0-1 背包问题。

另一种简单的思路是将它看成一个拥有$N$ 件物品的 0-1 背包问题，但是第$i$ 件物品的体积和价值有$num[i]$种取值。

我们以第二种方法为例，采用与第二章 0-1 背包问题相似的解法，可以写出 Core Code。
```java
for (int i = 1; i <= N; ++i) {
    for (int j = 0; j <= V; ++j) {
        backpack[i][j] = backpack[i - 1][j];
        for (int k = 1; k <= num[i]; ++k) {
            if (j >= k * cap[i]) {
                backpack[i][j] = Math.max(backpack[i][j], backpack[i - 1][j - k * cap[i]] + k * val[i]);
            }
        }
    }
}
```
时间复杂度 $O(V\sum num[i])$，空间复杂度$O(NV)$。

同样，我们可以使用**一维数组优化**，优化算法的空间复杂度。??
```
k = 1, j = v, f[v] = Math.max(f[v], f[v - cap[i]] + val[i])
k = 2, j = v, f[v] = Math.max(f[v], f[v - cap[i]] + val[i])
//当k = 2时,f[v] 已经加过一次val[i]
但是cap[i]并没有再减一次？
```
```java
for (int i = 1; i <= N; ++i) {
    for (int k = 1; k <= num[i]; ++k) {
        for (int j = V; j >= cap[i]; --j) {
            f[j] = Math.max(f[j], f[j - cap[i]] + val[i]);
        }
    }
}
```
时间复杂度 ==$O(V\sum num[i])$==，空间复杂度 $O(V)$。

## 4. 完全背包问题

**问题概述**

>有 $N$ 种物品，一个容量为 $V$ 的背包，每种物品都有`无限件可用`。第 $i$ 种物品的体积为$cap[i]$，价值为 $val[i]$。求在不超过背包的容量限制的情况下所能获得的最大总价值和为多少？

这个问题与 0-1 背包和多重背包都不同，前者每种物品只有 1 件，后者每种物品有 $num[i]$ 件。而对于完全背包来讲，**每种物品有无限件**。

**问题分析**
虽然题目声称每种物品都有无限件，但实际上每种物品最多有 $V / cap[i]$件，因此这个问题相当于一个 $num[i] = V / cap[i]$ 的多重背包问题。

根据第三章的思路，可以得到 Core Code。

```java
for (int i = 1; i <= N; ++i) {
    for (int k = 1; k * cap[i] <= V; ++k) {
        for (int j = V; j >= cap[i]; --j) {
            f[j] = Math.max(f[j], f[j - cap[i]] + val[i]);
        }
    }
}
```

### [440. Backpack III - LintCode](https://www.lintcode.com/problem/backpack-iii/description)

> 给定 n 种具有大小 A[i] 和价值 V[i] 的物品（每个物品可以取用无限次）和一个大小为 m 的一个背包, 你可以放入背包里的最大价值是多少?
注意事项：你不能将物品分成小块, 选择的项目的总大小应小于或等于 m。

有了上面的基础，我们可以发现这是一个“裸”的完全背包问题，根据上面的模板可以得到代码。

```java
public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        int n = A.length;
        int[] f = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            for (int k = 1; k * A[i] <= m; ++k) {
                for (int j = m; j >= A[i]; --j) {
                    f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
                }
            }
        }
        return f[m];
    }
}
```
时间复杂度$ O(m\sum \frac{m}{A[i]})$，空间复杂度 $O(m)$。

**时间复杂度优化**
目前我们得到了一个时间复杂度为 $O(m\sum A[i])$的算法来解决完全背包问题。
我们只需要将 j 的循环由逆向转变为正向就可以优化时间复杂度。由于同一种物品的个数无限，所以我们可以在任意容量 $j$ 的背包尝试装入当前物品，$j$ 从小向大枚举可以保证所有包含第 $i$ 种物品，体积不超过 $j - A[i]$ 的状态被枚举到。
从而得到时间复杂度为 $O(mn)$，空间复杂度为 $O(m)$ 的优秀算法。

```java
public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        int n = A.length;
        int[] f = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = A[i]; j <= m; ++j) {
                f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
            }
        }
        return f[m];
    }
}
```

### [562. Backpack IV - LintCode](https://www.lintcode.com/problem/backpack-iv/description)

>给出 $n$ 个物品，以及一个数组，$nums[i]$ 代表第 $i$ 个物品的大小，保证大小均为正数并且没有重复，正整数$ target$ 表示背包的大小，找到能填满背包的方案数。
注意事项：`每一个物品可以使用无数次`。

设 $backpack[i][j]$ 代表前 $i$ 个物品选则若干个，所选物品体积恰为 $j$ 的方案数，则有状态转移方程：
$backpack[i][j] = backpack[i - 1][j - k * nums[i]]$
$backpack[0][0] = 1$
$backpack[0][i] = 0$

使用一维数组优化，可得 Java 代码如下：
```java
public class Solution {
    public int backPackIV(int[] nums, int target) {
        // write your code here
        int n = nums.length;
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = nums[i]; j <= target; j++) {
                f[j] += f[j - nums[i]];
            }
        }
        return f[target];
    }
}
```

## 5. 扩展——伪多项式时间算法

**什么是伪多项式时间**
>问题：0-1 背包时间复杂度为 $O(VN)$ 的动态规划解法是多项式时间算法吗？其中，$V$ 表示背包容量，$N$ 表示物品个数。

这个问题可以说是，也可以说不是。为什么呢？

说是，是因为 $O(VN)$，相对于 $V$ 和 $N$ 这两个数字值是多项式时间的。

说不是，是因为 $O(VN)$，相对于输入所需要的位的长度 $O(logV + Nlog(max(cap[i], val[i]))$ 是指数时间的。

我们一般把运行时间是关于**输入规模**（输入所需要的位的长度）为指数时间，而关于输入的**数字值**为多项式时间的时间称为伪多项式时间。

**伪多项式时间算法举例**
我们在这里举一个简单的例子，通过试除法判断一个数 $n$ 是否为质数，需要 $O(\sqrt{n})$ 的时间。

具体做法为，枚举 $[2, \sqrt{n}]$，判断是否存在数字 $i$ 使得 n % i == 0。若不存在，$n$ 为质数，否则 $n$ 不为质数。

在这里，$O(\sqrt{n})$关于 $n$ 的数字值为多项式时间，但是关于输入规模 $logn$ 为指数时间。

所以，这个算法被称为伪多项式时间算法。