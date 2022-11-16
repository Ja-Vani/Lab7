public class SExecuter extends Executer {

    private Collection collection;

    SExecuter(Collection collection) {
        this.collection = collection;
    }

    @Override
    public String pushId(String str, HumanBeing humanBeing, User user) {
        return collection.pushId(Long.parseLong(str), humanBeing, user);
    }

    @Override
    public String ril(String str, HumanBeing humanBeing, User user) {
        return collection.ril(Integer.parseInt(str), humanBeing, user);
    }

    @Override
    public String remove(String str, User user) {
        return collection.remove(Integer.parseInt(str), user);
    }

    @Override
    public String insert(String str, HumanBeing humanBeing, User user) {
        return collection.insert(Integer.parseInt(str), humanBeing, user);
    }

    @Override
    public String filterName(String str) {
        return collection.filterName(str);
    }

    @Override
    public String filterImpact(String str) {
        return collection.filterImpact(Long.parseLong(str));
    }

    @Override
    public String average() {
        return Double.toString(collection.average());
    }

    @Override
    public String clear(User user) {
        return collection.clear(user);
    }

    @Override
    public String getInfo() {
        return collection.getInfo();
    }

    @Override
    public String getElements() {
        return collection.getElements();
    }

    @Override
    public void save() {
        collection.save();
    }

    @Override
    public String login(User user) {
        return collection.login(user);
    }

    @Override
    public String register(User user) {
        return collection.register(user);
    }
}
