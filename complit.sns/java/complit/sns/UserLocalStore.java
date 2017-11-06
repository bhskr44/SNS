package complit.sns;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserLocalStore {
    public static final String sp = "UserDetails";
    SharedPreferences LocalDatabase;

    public UserLocalStore(Context context) {
        this.LocalDatabase = context.getSharedPreferences(sp, 0);
    }

    public void StoreData(User user) {
        Editor speditor = this.LocalDatabase.edit();
        speditor.putString("stuname", user.stuname);
        speditor.putString("sturoll", user.sturoll);
        speditor.putString("stuemail", user.stuemail);
        speditor.putString("stupassword", user.stupassword);
        speditor.putInt("stuphone", user.stuphone);
        speditor.putInt("stusem", user.stusem);
        speditor.commit();
    }

    public User LoggedinUser() {
        return new User(this.LocalDatabase.getString("stuname", BuildConfig.FLAVOR), this.LocalDatabase.getString("sturoll", BuildConfig.FLAVOR), this.LocalDatabase.getString("stuemail", BuildConfig.FLAVOR), this.LocalDatabase.getString("stupassword", BuildConfig.FLAVOR), this.LocalDatabase.getInt("stusem", -1), this.LocalDatabase.getInt("stuphone", -1));
    }

    public void setUserLogin(boolean login) {
        Editor speditor = this.LocalDatabase.edit();
        speditor.putBoolean("login", login);
        speditor.commit();
    }

    public boolean getuserlogin() {
        if (this.LocalDatabase.getBoolean("login", false)) {
            return true;
        }
        return false;
    }

    public void clear() {
        Editor speditor = this.LocalDatabase.edit();
        speditor.clear();
        speditor.commit();
    }
}
