package org.example.slot.custom.assertj;

import org.assertj.core.api.ObjectAssert;
import org.example.slot.models.rest.player.NewPlayerRequest;
import org.example.slot.models.rest.player.Player;

public class NewPlayerResponseAssert extends ObjectAssert<Player> {
    public NewPlayerResponseAssert(Player resp) {
        super(resp);
    }

    public static NewPlayerResponseAssert assertThat(Player resp) {
        return new NewPlayerResponseAssert(resp);
    }

    public NewPlayerResponseAssert playerCreatedValid() {
        extracting(Player::id).isNotNull();
        extracting(Player::isVerified).isNotNull().isEqualTo(false);
        extracting(Player::bonusAllowed).isNotNull().isEqualTo(true);
        return this;
    }

    public NewPlayerResponseAssert isEqualToSendedRequest(NewPlayerRequest req) {
        isNotNull();
        extracting(Player::username).isNotNull().isEqualTo(req.username());
        extracting(Player::email).isNotNull().isEqualTo(req.email());
        extracting(Player::name).isNotNull().isEqualTo(req.name());
        extracting(Player::surname).isNotNull().isEqualTo(req.surname());
        return this;
    }
}
