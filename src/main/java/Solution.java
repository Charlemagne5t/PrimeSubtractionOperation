import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean primeSubOperation(int[] nums) {
        SieveOfEratosthenes sieve = new SieveOfEratosthenes();
        List<Integer> primes = sieve.generatePrimes(1000);

        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i - 1] >= nums[i]) {
                int target = nums[i - 1] - nums[i] + 1;
                int primeT = find(primes, target);
                if (primeT == -1) {
                    return false;
                }
                nums[i - 1] = nums[i - 1] - primeT;
                if (nums[i - 1] < 1) {
                    return false;
                }
            }
        }

        return true;
    }

    int find(List<Integer> sieve, int target) {
        int l = 0;
        int r = sieve.size() - 1;
        int mid;
        int res = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (sieve.get(mid) == target) {
                return target;
            }
            if (sieve.get(mid) > target) {
                r = mid - 1;
                res = sieve.get(mid);
            } else {
                l = mid + 1;
            }

        }
        return res;
    }
}

class SieveOfEratosthenes {

    private boolean[] isPrime;

    public List<Integer> generatePrimes(int limit) {
        isPrime = new boolean[limit + 1];
        List<Integer> primes = new ArrayList<>();

        initializeSieve(limit);
        applySieve(limit);
        collectPrimes(primes, limit);

        return primes;
    }

    private void initializeSieve(int limit) {
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }
    }

    private void applySieve(int limit) {
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    private void collectPrimes(List<Integer> primes, int limit) {
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }
}

