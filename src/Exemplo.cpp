#include <iostream>
using namespace std;

int main() {
  int n;
  int r;
  cout << "Digite um numero:";
  cin >> n;
  for (int i; i <= 10; i++) {
    r = n * i;
    cout << i;
    cout << " x ";
    cout << n;
    cout << " = ";
    cout << r;
  }
  return 0;
}
