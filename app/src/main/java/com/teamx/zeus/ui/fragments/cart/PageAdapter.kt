import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamx.zeus.ui.fragments.cart.CartFragment
import com.teamx.zeus.ui.fragments.checkout.CheckoutFragment
import com.teamx.zeus.ui.fragments.paymentMethod.PaymentFragment
import com.teamx.zeus.ui.fragments.profile.ProfileFragment
import com.teamx.zeus.ui.fragments.rateApp.RateAppFragment

class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return CartFragment()
            }

            1 ->  {
                return CheckoutFragment()

            }

            2 ->{
                return  PaymentFragment()
            }

            else -> {
                return Fragment()
            }
        }
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        when(position) {
//            0 -> {
//                return "Cart"
//            }
//            1 -> {
//                return "Checkout"
//            }
//            2 -> {
//                return "Payment Method"
//            }
//        }
//        return super.getPageTitle(position)
//    }

}