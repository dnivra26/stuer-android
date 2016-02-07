package dnivra26.github.io.stuer;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ReverseGeoCoder {

    public static String reverse(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addressList = null;
        try {
            addressList = geocoder.getFromLocation(
                    lat, lng, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }
//                sb.append(address.getLocality()).append("\n");
//                sb.append(address.getPostalCode()).append("\n");
//                sb.append(address.getCountryName());
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NA";
    }
}
