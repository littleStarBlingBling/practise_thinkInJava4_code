package com.practise.enumerated;

import com.practise.utils.EnumUtils;

import java.util.Iterator;

class Mail {
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {
        UN_SCANN_ABLE, YES1, YES2, YES3, YES4
    }

    enum Readability {
        ILLEGIBLE, YES1, YES2, YES3, YES4
    }

    enum Address {
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6
    }

    enum ReturnAddress {
        MISSING, OK1, OK2, OK3, OK4, OK5
    }

    // 正常投送
    GeneralDelivery generalDelivery;

    // 可扫描的
    Scannability scannability;

    // 可阅读的
    Readability readability;

    Address address;

    ReturnAddress returnAddress;

    private static long counter = 0;

    private long id = counter++;

    @Override
    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ", \nGeneral Delivery: " + generalDelivery +
                ", \nAddress Scannability: " + scannability +
                ", \nAddress Readability: " + readability +
                ", \nAddress Address: " + address +
                ", \nReturn address: " + returnAddress;
    }

    // 生成测试邮件
    public static Mail randomMail() {
        Mail mail = new Mail();
        mail.generalDelivery = EnumUtils.random(GeneralDelivery.class);
        mail.scannability = EnumUtils.random(Scannability.class);
        mail.readability = EnumUtils.random(Readability.class);
        mail.address = EnumUtils.random(Address.class);
        mail.returnAddress = EnumUtils.random(ReturnAddress.class);
        return mail;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {

            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

public class PostOffice {
    enum MailHandler {
        // 值为 YES 的正常投递
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail mail) {
                switch (mail.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for " + mail);
                        return true;
                    default:
                        return false;
                }
            }
            // 可以扫描的，再判断一下投递地址是否正确
        }, MACHINE_SCAN {
            @Override
            boolean handle(Mail mail) {
                switch (mail.scannability) {
                    case UN_SCANN_ABLE:
                        return false;
                    default:
                        switch (mail.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + mail + " automatically");
                                return true;
                        }
                }
            }
            // 不能机器扫描就人工处理，也要判断地址是否正确
        }, VISUAL_INSPECTION {
            @Override
            boolean handle(Mail mail) {
                switch (mail.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (mail.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + mail + " normally");
                                return true;
                        }
                }
            }
            // 前面的都不能处理，只有返还寄信人
        }, RETURN_TO_SENDER {
            @Override
            boolean handle(Mail mail) {
                switch (mail.returnAddress){
                    case MISSING:
                        return false;
                    default:
                        System.out.println("Returning " + mail + " to sender");
                        return true;
                }
            }
        },;

        abstract boolean handle(Mail mail);
    }

    static void handle(Mail mail) {
        for (MailHandler handler : MailHandler.values()) {
            if (handler.handle(mail)) {
                return;
            }
        }

        System.out.println(mail + " is a dead letter");

    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(5)) {
            System.out.println(mail.details());
            handle(mail);
            System.out.println("----------");
        }
    }

}
