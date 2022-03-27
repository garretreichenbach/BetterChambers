package org.schema.game.common.data.blockeffects.config;

import org.schema.schine.common.language.Lng;

/**
 * StatusEffectCategory (Modified)
 */
public enum StatusEffectCategory {
    AI(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_0;
        }
    }),
    ARMOR(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_1;
        }
    }),
    CARGO(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_2;
        }
    }),
    OLD_CLOAKERS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_3;
        }
    }),
    DAMAGE(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_4;
        }
    }),
    FACTORIES(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_5;
        }
    }),
    GRAVITY(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_6;
        }
    }),
    JUMP(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_7;
        }
    }),
    MASS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_8;
        }
    }),
    MINING(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_9;
        }
    }),
    POWER(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_10;
        }
    }),
    OLD_RADARJAM(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_11;
        }
    }),
    STEALTH(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_22;
        }
    }),
    RAILS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_12;
        }
    }),
    REACTOR(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_13;
        }
    }),
    SCANNERS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_14;
        }
    }),
    SHIELDS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_15;
        }
    }),
    SHIPYARDS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_24;
        }
    }),
    THRUSTERS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_16;
        }
    }),
    TRANSPORTERS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_17;
        }
    }),
    VISIBILITY(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_18;
        }
    }),
    WARHEADS(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_19;
        }
    }),
    WARP(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_20;
        }
    }),
    WEAPON(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_21;
        }
    }),
    BUILDING(new Object() {
        public final String toString() {
            return Lng.ORG_SCHEMA_GAME_COMMON_DATA_BLOCKEFFECTS_CONFIG_STATUSEFFECTCATEGORY_23;
        }
    }),
    //INSERTED CODE
    AURA(new Object() {
       public final String toString() {
           return "Aura";
        }
    });
    //

    private final Object nameObj;

    private StatusEffectCategory(Object var3) {
        this.nameObj = var3;
    }

    public final String getName() {
        return this.nameObj.toString();
    }
}