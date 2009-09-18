package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The class defines all NamedQueries used by Domain objects.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - formatted queries
 */
@NamedQueries({
        @NamedQuery(name = "findCharityByCharityName",
                query = "SELECT charity.id, charity.name, charity.registeredCharityNumber "
                + " FROM Charity charity "
                + " WHERE "
                + " (charity.name LIKE :charitySearchString "
                + " OR charity.commonName LIKE :charitySearchString "
                + " OR charity.registeredCharityNumber LIKE :charitySearchString) "
                + " ORDER BY charity.name, charity.registeredCharityNumber"),
        @NamedQuery(name = "fetchCharityDetailsByVmgCharityUrl",
                query = "SELECT charity "
                + " FROM Charity charity "
                + " WHERE charity.vmgCharityURL = :vmgCharityUrl"),
        @NamedQuery(name = "loadCharityAlertTrigger",
                query = "SELECT  OBJECT(alertTrigger) "
                + " FROM AlertTrigger alertTrigger "
                + " LEFT JOIN FETCH alertTrigger.alertUsers alu "
                + " WHERE  alertTrigger.charity.id = :charityId "
                + " AND alu.charityAdministrator.userRole.user.endDatetime IS NULL"),

        @NamedQuery(name = "findCharityAdministratorUsers",
                query = "SELECT  charityAdmin.id , p.forename, p.surname "
                + " FROM CharityAdministrator charityAdmin, UserRole uRole, User u, Person p "
                + " WHERE  charityAdmin.userRole = uRole.id "
                + " AND uRole.user =  u.id "
                + " AND u.person = p.id "
                + " AND u.endDatetime IS NULL "
                + " AND charityAdmin.charity.id = :charityId "
                + " ORDER BY p.forename, p.surname"),
        @NamedQuery(name = "fetchModuleData",
                query = "SELECT  OBJECT(offMod) "
                + " FROM OfflineRegModule offMod "
                + " WHERE offMod.module.code = :moduleCode"),
        @NamedQuery(name = "fetchCharityOfflineRegData1",
                query = "SELECT  OBJECT(charityOffReg) "
                + " FROM CharityOfflineRegistration charityOffReg "
                + " WHERE charityOffReg.charity.id = :charityId"),
        @NamedQuery(name = "fetchCharityOfflineRegData",
                query = "SELECT  OBJECT(charityOffReg) "
                + " FROM CharityOfflineRegistration charityOffReg "
                + " LEFT JOIN FETCH charityOffReg.charityOfflineRegistrationNotes notes "
                + " WHERE charityOffReg.charity.id = :charityId"),
        @NamedQuery(name="updateRegularDonationStatus",
            query = "UPDATE RegularDonation r "
                + " SET r.statusIndicator = :newStatus "
                + " WHERE r.donor.id = :donorId"
        ),
        @NamedQuery(name = "findCharitiesLikeCharityNameForStatus",
                query = "SELECT charity.id, charity.name, charity.registeredCharityNumber"
                + " FROM Charity charity"
                + " WHERE (charity.name LIKE :charitySearchString "
                + " or charity.commonName LIKE :charitySearchString) "
                + " and charity.charityStatus.code = :status "
                + " ORDER BY charity.name "),
        @NamedQuery(name = "findCharityByCharityRegistrationNumber",
                query = "SELECT OBJECT(charity) "
                + " FROM Charity charity "
                + " WHERE charity.registeredCharityNumber = :charityRegistrationNumber"),
        @NamedQuery(name = "isCharityRegistered",
                query = "SELECT COUNT(charity.id) "
                + " FROM Charity charity "
                + " WHERE charity.registeredCharityNumber = :charityRegistrationNumber "
                + " AND (charity.charityStatus = 'IRC' "
                + " OR  charity.charityStatus = 'LIV') "
                + "GROUP BY charity.id"),
        @NamedQuery(name = "isAccountExist",
                query = "SELECT user.id "
                + " FROM User user, Person person "
                + " WHERE user.person = person.id "
                + " AND user.loginEmailAddress = :emailAddress "
                + " AND person.dobDay = :dobDay "
                + " AND person.dobMonth = :dobMonth "
                + " AND person.dobYear = :dobYear"),
        @NamedQuery(name = "getCharityStatus",
                query = "SELECT charity.charityStatus.code "
                + " FROM Charity charity "
                + " WHERE charity.id = :charityId"),
        @NamedQuery(name = "fetchCharitiesByCategoryId",
                query = "SELECT CategorisedCharity.charity "
                + " FROM CategorisedCharity categorisedCharity "
                + " WHERE categorisedCharity.charityCategory.id = :charityCategoryId "
                + " AND categorisedCharity.charity.charityStatus.code = 'LIV' "
                + " ORDER BY categorisedCharity.charity.name"),
        @NamedQuery(name = "searchFundraisingEvent",
                query = "SELECT e.id, e.name, l.description, c.id, c.name, "
                + " e.eventDatetime, e.description, e.maximumCharities,e.fundraiserSplitOverrideInd,"
                + " ca.splitPercentage "
                + " FROM CharitableActivity ca, Charity c, Event e, Location l, ActivityType at , EventActivity ea "
                + " WHERE e.eventStatus.code = 'PUB'  "
                + " AND c.id = ca.charity.id "
                + " AND e.id = ca.event.id "
                + " AND l.isoCode = e.location.isoCode  "
                + " AND  ca.event.id = ea.event.id  "
                + " AND ea.activityType.code = at.code "
                + " AND e.name like :eventName "
                + " AND at.code like :activityType "
                + " AND c.name like :charityName "
                + " AND l.isoCode like :locationCode "
                + " AND e.eventDatetime between :startDate "
                + " AND :endDate "
                + " ORDER BY e.name, c.name, l.description, e.eventDatetime"),

        @NamedQuery(name = "searchFundraisingEvent_nocharity",
                query = "SELECT distinct e "
                + " FROM Event e "
                + " LEFT JOIN FETCH e.charitableActivities ca "
                + " JOIN FETCH e.location l "
                + " JOIN FETCH e.eventActivities ea "
                + " WHERE e.name LIKE :eventName "
                + " AND e.eventStatus.code = 'PUB'  "
                + " AND e.eventDatetime BETWEEN :startDate "
                + " AND :endDate AND l.isoCode LIKE :locationCode "
                + " AND ea.activityType.code LIKE :activityType "
                + " ORDER BY e.name,l.description, e.eventDatetime"),
        @NamedQuery(name = "searchFundraisingEvent_charity", query = "SELECT distinct e "
                + "FROM Event e "
                + "LEFT JOIN FETCH e.charitableActivities ca left "
                + "join fetch ca.charity c "
                + "join fetch e.location l "
                + "join fetch e.eventActivities ea "
                + "where e.name like :eventName "
                + " AND e.eventStatus.code = 'PUB'  "
                + "and e.eventDatetime between :startDate "
                + "and :endDate and l.isoCode like :locationCode "
                + "and ea.activityType.code like :activityType "
                + "and e.id "
                + "in (select e2.id "
                + "from Event e2 inner JOIN e2.charitableActivities ca2 inner join ca2.charity c2 "
                + "where c2.name like :charityName) "
                + "ORDER BY e.name,c.name,l.description, e.eventDatetime"),
        @NamedQuery(name = "searchFundraisingEventbyDescription_nocharity",
                query = "SELECT distinct e "
                + " FROM Event e "
                + " LEFT JOIN FETCH e.charitableActivities ca "
                + " JOIN FETCH e.location l "
                + " JOIN FETCH e.eventActivities ea "
                + " WHERE (e.name NOT LIKE :eventName AND e.description LIKE :eventDescription) "
                + " AND e.eventStatus.code = 'PUB'  "
                + " AND e.eventDatetime BETWEEN :startDate AND :endDate "
                + " AND l.isoCode LIKE :locationCode "
                + " AND ea.activityType.code LIKE :activityType "
                + " ORDER BY e.name,l.description, e.eventDatetime"),
        @NamedQuery(name = "searchFundraisingEventbyDescription_charity",
                query = "SELECT distinct e "
                + " FROM Event e "
                + " LEFT JOIN FETCH e.charitableActivities ca "
                + " left join fetch ca.charity c "
                + " join fetch e.location l "
                + " join fetch e.eventActivities ea "
                + " where (e.name not like :eventName  "
                + " and e.description like :eventDescription) "
                + " AND e.eventStatus.code = 'PUB'  "
                + " and e.eventDatetime between :startDate and :endDate "
                + " and l.isoCode like :locationCode "
                + " and ea.activityType.code like :activityType "
                + " and c.name like :charityName "
                + " ORDER BY e.name,c.name,l.description, e.eventDatetime"),

        @NamedQuery(name = "searchFundraisingEventbyDescription",
                query = "SELECT e.id, e.name, l.description, c.id, c.name,  e.eventDatetime,"
                + " e.description, e.maximumCharities "
                + " FROM CharitableActivity ca, Charity c, Event e, Location l,"
                + " ActivityType at , EventActivity ea "
                + " WHERE e.eventStatus.code = 'PUB' "
                + " and c.id = ca.charity.id "
                + " and e.id = ca.event.id "
                + " and l.isoCode = e.location.isoCode "
                + " and ca.event.id = ea.event.id  "
                + " and ea.activityType.code = at.code and (e.name not like :eventName "
                + " and e.description like :eventDescription) and at.code like :activityType "
                + " and c.name like :charityName and l.isoCode like :locationCode "
                + " and e.eventDatetime between :startDate and :endDate "
                + " ORDER BY e.name, c.name, l.description, e.eventDatetime"),
        @NamedQuery(name = "fetchPaymentcardDetails",
                query = "SELECT paymentCard FROM PaymentCard paymentCard WHERE paymentCard.person.id = :personId"),
        @NamedQuery(name = "countNumberOfURLs",
                query = "SELECT COUNT(ud) FROM UrlDetails ud WHERE ud.urlType.code = :urlType and ud.url = :url"),
        @NamedQuery(name = "getNumberOfRegisteredUsersWithSameName",
                query = "SELECT COUNT(u) "
                + " FROM User u inner join u.person p "
                + " WHERE p.forename = :firstname and p.surname = :lastname"),
        @NamedQuery(name = "getUserByEmailAndDob",
                query = "SELECT u FROM User u inner join u.person p "
                + " WHERE u.loginEmailAddress = :emailAddress and p.dobDay = :dayOfBirth "
                + " and p.dobMonth = :monthOfBirth and p.dobYear = :yearOfBirth"),
        @NamedQuery(name = "fetchUserRoles",
                query = "SELECT ur FROM UserRole ur inner join ur.user u inner join u.person p "
                + " WHERE u.loginEmailAddress = :emailAddress and p.dobDay = :dobDay "
                + " and p.dobMonth = :dobMonth and p.dobYear = :dobYear"),
        @NamedQuery(name = "fetchPersonDetails",
                query = "select p from Person p, User u "
                + " where p.id = u.person and u.loginEmailAddress = :emailAddress "
                + " and p.dobDay = :dobDay and p.dobMonth = :dobMonth and p.dobYear = :dobYear"),
        @NamedQuery(name = "isPaymentCardDetailsExist", query = "select pc "
                + "from PaymentCard pc, Person p, User u "
                + "where pc.person = p.id and p.id = u.person "
                + "and u.loginEmailAddress = :emailAddress "
                + "and p.dobDay = :dobDay " + "and p.dobMonth = :dobMonth "
                + "and p.dobYear = :dobYear"),
        @NamedQuery(name = "fetchAllActivityTypes", query = "Select at.code, at.description, at.fundraisingReason.code "
                + "FROM ActivityType at "
                + "WHERE at.fundraisingReason.code =  :reason"
                + " OR at.fundraisingReason.code = :otherReason"
                + " ORDER BY at.code"),
        @NamedQuery(name = "fetchDonorIdByUserRoleId", query = "Select donor.id "
                + "FROM Donor donor " + "WHERE donor.userRole.id = :id"),
        @NamedQuery(name = "fetchAllCharityCategories",
                query = "select charityCategory FROM CharityCategory charityCategory "
                + " ORDER BY charityCategory.categoryTitle, charityCategory.subcategoryTitle"),
        @NamedQuery(name = "fetchDonorDetails",
                query = "SELECT d FROM Donation donation, Donor d WHERE donation.id = :id and donation.donor = d.id"),

        @NamedQuery(name = "fetchPersonDetailsOfDonation",
                        query = "SELECT p FROM Donation donation, Person p WHERE donation.id = :id and donation.person = p.id"),

        @NamedQuery(name = "fetchBankAccountForCharity",
                query = "SELECT bankAccount FROM BankAccount bankAccount "
                + " inner join bankAccount.bank.bankAddress bankAddress WHERE bankAddress.endDatetime IS NULL "
                + " AND bankAccount.charity.id = :charityId ORDER BY bankAccount.accountDescription DESC"),

        @NamedQuery(name = "fetchCharityBankDetailsForEvent",
                query = "SELECT bankAccount FROM BankAccount bankAccount "
                + " inner join bankAccount.bank.bankAddress bankAddress WHERE "
                + " bankAccount.accountStatus.code = 'ACT' "
                + " AND bankAddress.endDatetime IS NULL "
                + " AND bankAccount.charity.id = :charityId "
                + " AND bankAccount.bankAccountType.code IN (:bankAccountTypeList) "
                + " ORDER BY bankAccount.accountDescription DESC"),

        @NamedQuery(name = "checkEventAttachedWithBank",
                query = "select es.code from CharitableActivity ca join ca.bankAccountFundraising baf "
                + " join ca.event e join e.eventStatus es where baf.id = :bankAccountId"),

        @NamedQuery(name = "fetchBankAccountForBank",
                query = "SELECT bankAccount FROM BankAccount bankAccount "
                + "inner join bankAccount.bank.bankAddress bankAddress WHERE bankAddress.endDatetime IS NULL "
                + " AND bankAccount.id = :bankAccountId"),

        @NamedQuery(name = "fetchCharityBankAccountType",
                query = "SELECT bankAccountType FROM BankAccountType bankAccountType"),

        @NamedQuery(name = "fetchCharityHomePageDetails",
                query = "SELECT page FROM Page page WHERE page.charity.id = :charityId "
                + "and page.pageStatus.code = 'ACT' and (page.pageType.code = 'CHA' or page.pageType.code = 'HOME')"),

        @NamedQuery(name = "fetchListofPageWidgetsById",
                query = "SELECT pageWidget FROM PageWidget pageWidget WHERE pageWidget.page.id = :pageId"),

        @NamedQuery(name = "fetchAllVmgCharityURLs", query = "SELECT charity.vmgCharityURL FROM Charity charity"),

        @NamedQuery(name = "findRegularDonationsByDonorId", query = "SELECT rd from RegularDonation rd "
                + "where rd.donor.id = :donorId AND rd.statusIndicator = 'ACTIVE'"),

        @NamedQuery(name = "fetch9RandomFundraisers",
                query = "select fr.fundraiser from FundraiserActivity fr,FundraisingCharitySplit fcs "
                + " where fr.id=fcs.fundraiserActivity.id "
                + " and fr.event.id=:eventId and fcs.charity.id=:charityId"),
        @NamedQuery(name = "fetchCharityLogo", query = "select c from Charity c where c.id=:charityId"),
        @NamedQuery(name = "fetchEventDetails", query = "select e from Event e where e.id=:eventId"),
        @NamedQuery(name = "searchOwnedOrganisedEvents",
                query = "select distinct(ca.event) from CharitableActivity ca "
                + " WHERE  ca.charity.id = :charityId and (ca.eventCreatorInd = 'Y' "
                + " OR (ca.eventCreatorInd = 'N' and ((ca.event.eventStatus.code IN ('PUB','EXP')) "
                + " OR (ca.event.eventStatus.code = 'CAN' and  ca.event.eventPrevStatus.code = 'PUB'))))"
                + " and ca.event.eventStatus.code in (:selectedStatus) order by ca.event.name"),
        @NamedQuery(name = "searchOwnAuthOrganisedEvents",
                query = "select distinct(ca.event) from CharitableActivity ca "
                + " JOIN ca.charityEventAdminDetails cea WHERE  ca.charity.id = :charityId "
                + " and ca.event.eventStatus.code in (:selectedStatus) "
                + " and cea.charityAdministrator.userRole.user.id = :userId "),
        @NamedQuery(name = "searchOwnedFourLatestOrganisedEvents",
                query = "select distinct(ca.event) from CharitableActivity ca"
                + " WHERE  ca.charity.id = :charityId and"
                + " ca.event.eventStatus.code in (:selectedStatus) order by ca.event.createdDateTime DESC"),
        @NamedQuery(name = "fetchOperationalOrganisedEvents",
                query = "select e from Event e WHERE e.operationalEventInd = 'Y' "
                + " and e.eventStatus.code in (:selectedStatus) order by e.eventDatetime, e.name "),
        @NamedQuery(name = "fetchAllCharityEventAdminList",
                query = " SELECT ca.id, p.title, p.forename, p.surname, u.loginEmailAddress FROM "
                + " CharityEventAdministrator cea, "
                + " CharityAdministrator ca, "
                + " UserRole ur, "
                + " User u, "
                + " Person p "
                + " WHERE "
                + " cea.charitableActivity.id = :charitableActivityId AND "
                + " cea.charityAdministrator.id = ca.id AND "
                + " ca.userRole.id = ur.id AND "
                + " ur.user.id = u.id AND "
                + " u.person.id = p.id " + " ORDER BY p.forename, p.surname"),
        @NamedQuery(name = "fetchAllCharityAdminList",
                query = " SELECT DISTINCT(p.id), ca.id, p.title, p.forename, "
                + " p.surname, ea.emailAddress FROM "
                + " CharityAdministrator ca "
                + " JOIN ca.userRole ur "
                + " JOIN ur.user u "
                + " JOIN u.userAuthGroups uag "
                + " JOIN uag.authGroup aug "
                + " JOIN u.person p "
                + " JOIN p.personEmailAddresses pea "
                + " JOIN pea.emailAddress ea "
                + " WHERE "
                + " ca.charity.id = :charityId "
                + " AND u.endDatetime IS NULL "
                + " AND aug.code = 'GROUP_CHARITY_EVENTS'"
                + " AND ea.emailAddressType = 'C' "
                + " ORDER BY p.forename, p.surname "),
        @NamedQuery(name = "isFundraiserTeamAlreadyExist", query = "select fg.name "
                + "from FundraiserGroup fg "
                + "where fg.name = :teamName and fg.fundraiserGroupType.code = 'TEAM'"),
        @NamedQuery(name = "fetchCharityIdByHmrcReference",
                query = "SELECT co.charity.id, co.charityHmrcRefNumber "
                + " FROM CharityOfflineRegistration co WHERE co.charityHmrcRefNumber = :hmrcRef"),
        @NamedQuery(name = "fetchCharityHmrcReferenceById",
                query = "SELECT co.charity.id, co.charityHmrcRefNumber "
                + " FROM CharityOfflineRegistration co WHERE co.charity.id = :charityId"),
        @NamedQuery(name = "fetchCharityTrusteeDetails",
                query = "SELECT td FROM TrusteeDetails td "
                + " WHERE td.charityDetails.id = :charityId"),
        @NamedQuery(name = "deleteCharityTrusteeDetails",
                query = "DELETE FROM TrusteeDetails td "
                + " WHERE td.trusteeType.code = :trusteeTypeCode "
                + " AND td.charityDetails.id = :charityId"),
        @NamedQuery(name = "fetchEventFeeList", query = " SELECT e.id, e.name, ca.paymentInstructions, c.id, c.name, "
                + " ca.alternateFeePayInd, ef "
                + " FROM "
                + " Event e, "
                + " CharitableActivity ca, "
                + " Charity c, "
                + " EventFeeDetails ef "
                + " WHERE "
                + " e.id = :eventId "
                + " AND e.id = ca.event.id "
                + " AND ca.eventCreatorInd = 'Y' "
                + " AND ca.vmgManageFeeInd = 'Y' "
                + " AND ca.charity.id = c.id "
                + " AND ca.id = ef.charitableActivity.id "
                + " order by ef.feeAmount "),
        @NamedQuery(name = "fetchEventFeeStatus", query = " SELECT e "
                + " FROM "
                + " Event e, "
                + " CharitableActivity ca "
                + " WHERE "
                + " e.id = :eventId "
                + " AND e.id = ca.event.id "
                + " AND ca.eventCreatorInd = 'Y' "
                + " AND ca.vmgManageFeeInd = 'Y' "
                + " AND (ca.onlineClosureDate IS NULL OR ca.onlineClosureDate >= CURRENT_DATE) "
                + " AND (ca.onlineEntryLimit IS NULL OR ca.onlineEntryLimit > ( "
                + " SELECT count(fa.id) "
                + " FROM FundraiserActivity fa "
                + " WHERE fa.event.id = :eventId "
                + " AND fa.feeSituation IS NOT NULL))"),

        @NamedQuery(name = "fetchCancelEventRight", query = " SELECT ca "
                + " FROM "
                + " Event e, "
                + " CharitableActivity ca "
                + " WHERE "
                + " e.id = ca.event.id "
                + " AND e.id = :eventId "
                + " AND ca.charity.id = :charityId "
                + " AND (ca.eventCreatorInd = 'Y' OR e.maximumCharities is null)"),
        @NamedQuery(name = "fetchCharityAdministratorEmailList", query = " SELECT "
                + " DISTINCT(p.id), p.title, p.forename, p.surname, "
                + " ea.emailAddress, ca.eventCreatorInd, ca.charity.id  "
                + " FROM CharityEventAdministrator cea "
                + " JOIN cea.charitableActivity ca "
                + " JOIN cea.charityAdministrator cam "
                + " JOIN cam.userRole ur "
                + " JOIN ur.user u "
                + " JOIN u.person p "
                + " JOIN p.personEmailAddresses pea "
                + " JOIN pea.emailAddress ea "
                + " WHERE ea.emailAddressType = 'C'"
                + " AND ca.event.id = :eventId " + " ORDER BY ca.charity.id "),
        @NamedQuery(name = "SearchRegisteredFundraiserByEmailAddress", query = " SELECT "
                + " DISTINCT(f.id), p.forename, p.surname, ea.emailAddress, ea.emailAddressType "
                + " FROM "
                + " Fundraiser f "
                + " JOIN f.userRole ur "
                + " JOIN ur.user u "
                + " JOIN u.person p "
                + " JOIN p.personEmailAddresses pea "
                + " JOIN pea.emailAddress ea"
                + " WHERE "
                + " f.fundraiserStatus.code = 'REGISTERED' and "
                + " ea.emailAddress like :emailAddress"
                + " ORDER BY ea.emailAddressType, p.forename, p.surname, ea.emailAddress "),
      @NamedQuery(name = "SearchRegisteredFundraiserByEmailAddressTemp", query = "  SELECT "
                + " DISTINCT(f.id) "
                + " FROM "
                + " Fundraiser f "
                + " JOIN f.userRole ur "
                + " JOIN ur.user u "
                + " JOIN u.person p "
                + " JOIN p.personEmailAddresses pea "
                + " JOIN pea.emailAddress ea"
                + " WHERE "
                + " f.fundraiserStatus.code = 'REGISTERED' and "
                + " ea.emailAddressType = :emailAddressTypeContact AND f.id IN ( SELECT "
                + " DISTINCT(f1.id) "
                + " FROM "
                + " Fundraiser f1 "
                + " JOIN f1.userRole ur1 "
                + " JOIN ur1.user u1 "
                + " JOIN u1.person p1 "
                + " JOIN p1.personEmailAddresses pea1 "
                + " JOIN pea1.emailAddress ea1"
                + " WHERE "
                + " ea1.emailAddress like :emailAddress AND ea1.emailAddressType = :emailAddressTypePersonal)"
                + " ORDER BY f.id "),
        @NamedQuery(name = "findPageDetailsbyFundraiserId", query = "  SELECT "
                + " p.id, p.title, p.fundraiser.id , p.fundraiserActivity.fundraiserGroup.id, p.url  "
                + " FROM " + " Page p " + " WHERE "
                + " p.fundraiser.id = :fundraiserId" + " ORDER BY p.title"),
        @NamedQuery(name = "fetchUserAuthorisationGroupsForInternalUser", query = "SELECT uag.authGroup.code "
                + " FROM UserAuthGroup uag "
                + " JOIN uag.user user "
                + " WHERE uag.endDatetime IS NULL"
                + " AND user.username = :username"),
        @NamedQuery(name = "fetchUserRoleCodesForInternalUser", query = "SELECT role.code "
                + " FROM UserRole ur "
                + " JOIN ur.role role "
                + " JOIN ur.user user "
                + " WHERE ur.endDatetime IS NULL "
                + " AND user.username = :username"),
        @NamedQuery(name = "fetchUserAuthorisationGroupsForExternalUser", query = "SELECT uag.authGroup.code "
                + " FROM UserAuthGroup uag "
                + " JOIN uag.user user "
                + " JOIN user.person person "
                + " WHERE uag.endDatetime IS NULL"
                + " AND user.loginEmailAddress = :loginEmailAddress"
                + " AND person.dobDay = :dobDay"
                + " AND person.dobMonth = :dobMonth"
                + " AND person.dobYear = :dobYear"),
        @NamedQuery(name = "fetchUserRoleCodesForExternalUser", query = "SELECT role.code "
                + " FROM UserRole ur "
                + " JOIN ur.role role "
                + " JOIN ur.user user "
                + " JOIN user.person person "
                + " WHERE ur.endDatetime IS NULL "
                + " AND user.loginEmailAddress = :loginEmailAddress"
                + " AND person.dobDay = :dobDay"
                + " AND person.dobMonth = :dobMonth"
                + " AND person.dobYear = :dobYear"),
        @NamedQuery(name = "findFundraiserGroupsbyFundraiserId", query = "SELECT "
                + " fgm.fundraiser.id, fg.id, fg.name, fg.description, fgm.id "
                + " FROM "
                + " FundraiserGroupMember fgm , FundraiserGroup fg "
                + " WHERE "
                + " fgm.fundraiserGroup.id = fg.id"
                + " and fgm.fundraiser is not null"
                + " and fg.fundraiserGroupType.code = :fundraiserGroupType "
                + " and fgm.endDatetime is null"
                + " and fgm.fundraiser.id = :fundraiserId"),
        @NamedQuery(name = "fetchFundraiserByUserId", query = "SELECT f "
                + "FROM User u, UserRole ur, Fundraiser f "
                + "WHERE u.id = :userId and u.id = ur.user.id and ur.id = f.userRole.id"),
        @NamedQuery(name = "fetchEventIdsByFundraiserId", query = "SELECT e.id "
                + "FROM FundraiserActivity fa, Event e "
                + "WHERE fa.fundraiser.id = :fundraiserId and fa.event.id = e.id"),
        @NamedQuery(name = "fetchCharityByUserId", query = "SELECT c "
                + "FROM User u, UserRole ur, CharityAdministrator ca, Charity c "
                + "WHERE u.id = :userId and u.id = ur.user.id and ur.id = ca.userRole.id and ca.charity.id = c.id"),
        @NamedQuery(name = "fetchEventIdsByCharityId", query = "SELECT e.id "
                + "FROM CharitableActivity ca, Event e "
                + "WHERE ca.charity.id = :charityId and ca.event.id = e.id"),
        @NamedQuery(name = "fetchDonorIdByUserId", query = "SELECT d.id "
                + "FROM User u, UserRole ur, Donor d "
                + "WHERE u.id = :userId and u.id = ur.user.id and ur.id = d.userRole.id"),

        @NamedQuery(name = "fetchCharityCustomFields", query = " SELECT ch "
                + " FROM " + " CharityCustomFieldSubset ch " + " WHERE "
                + " ch.charityId = :charityId "),
        @NamedQuery(name = "fetchCharityCustomFieldsByCode", query = " SELECT ch "
                + " FROM "
                + " CharityCustomFieldSubset ch "
                + " WHERE "
                + " ch.charityId = :charityId "
                + " AND ch.customFieldTypeCode=:customFieldTypeCode"),

        @NamedQuery(name = "fetchCustomFieldValuesByDonor", query = " SELECT ch "
                + " FROM "
                + " CharityCustomFieldValueSubset ch "
                + " WHERE "
                + " ch.charityCustomFieldId = :charityCustomFieldId"
                + " AND ch.donorId = :donorId"),
        @NamedQuery(name = "fetchCustomFieldValuesByEvent", query = " SELECT ch "
                + " FROM "
                + " CharityCustomFieldValueSubset ch "
                + " WHERE "
                + " ch.charityCustomFieldId = :charityCustomFieldId"
                + " AND ch.eventId = :eventId"),
        @NamedQuery(name = "fetchCustomFieldValuesByFundraiser", query = " SELECT ch "
                + " FROM "
                + " CharityCustomFieldValueSubset ch "
                + " WHERE "
                + " ch.charityCustomFieldId = :charityCustomFieldId"
                + " AND ch.fundraiserId = :fundraiserId"),
        @NamedQuery(name = "fetchCustomFieldValuesByFundraiserActivity", query = " SELECT ch "
                + " FROM "
                + " CharityCustomFieldValueSubset ch "
                + " WHERE "
                + " ch.charityCustomFieldId = :charityCustomFieldId"
                + " AND ch.fundraiserActivityId = :fundraiserActivityId"),


        @NamedQuery(name = "fetchPersonDetailsByFundraiserId", query = " SELECT p1 "
                + " FROM  Person p1 ,User u1, UserRole ur, Fundraiser f1"
                + " WHERE f1.id =:fundraiserId"
                + " AND ur.user.id=u1.id  and u1.person.id=p1.id and f1.userRole.id = ur.id"),

        @NamedQuery(name = "fetchFundraiserActivitiesByCharityId",
                query = " SELECT  fra "
                + " FROM FundraiserActivity fra, Fundraiser fr  "
                + " WHERE fr.id = fra.fundraiser.id "
                + "   AND fr.fundraiserStatus.code != 'FAKE' "
                + "   AND fra.id "
                + " IN "
                + " (SELECT fcsp.fundraiserActivity.id "
                + " FROM FundraisingCharitySplit fcsp "
                + " WHERE fcsp.charity.id = :charityId) "
                + " GROUP BY fra.id"),

        @NamedQuery(name = "fetchDonorsByCharityId",
                query = "SELECT dr FROM Donor dr , Donation dn1, CharityDonation cdn1 "
                + " WHERE cdn1.donation.id = dn1.id "
                + " AND dn1.donor.id = dr.id "
                + " AND cdn1.charity.id = :charityId"),
        @NamedQuery(name = "fetchDonationByDonorAndCharityId",
                query = "SELECT dn1 "
                + " FROM Donation dn1,CharityDonation cdn1"
                + " WHERE cdn1.donation.id = dn1.id "
                + " AND dn1.donor.id = :donorId "
                + " AND cdn1.charity.id = :charityId"),
        @NamedQuery(name = "fetchPersonDetailsByDonorId",
                query = " SELECT p1 "
                + " FROM  Person p1 ,User u1, UserRole ur, Donor d1"
                + " WHERE d1.id =:donorId"
                + " AND ur.user.id=u1.id "
                + " AND u1.person.id=p1.id "
                + " AND d1.userRole.id = ur.id"),

        @NamedQuery(name = "fetchEventsByCharityId",
                query = "SELECT event "
                + " FROM CharitableActivity cha "
                + " WHERE cha.charity.id = :charityId "),

        @NamedQuery(name = "fetchCharityById",
                query = "SELECT ch "
                + " FROM Charity ch "
                + " WHERE ch.id = :charityId "),

        @NamedQuery(name = "fetchOperationUsers",
                query = "select DISTINCT(user.username) "
                + " FROM UserRole ur "
                + " JOIN ur.role role "
                + " JOIN ur.user user "
                + " WHERE user.username IS NOT NULL "
                + " AND user.username != ''"
                + " AND (role.code = 'ROLE_O_US' OR role.code = 'ROLE_O_AD') "),

        @NamedQuery(name = "getUserById",
                query = "SELECT u "
                + " FROM User u "
                + " WHERE u.id = :userId"),
        @NamedQuery(name = "getUserByName",
                query = "SELECT u "
                + " FROM User u "
                + " WHERE u.username = :userName"),
        @NamedQuery(name = "fetchUserByUserName",
                query = "SELECT u "
                + " FROM User u "
                + " WHERE u.username  =:username "),
        @NamedQuery(name = "fetchFundraiserActivityDonations",
                query = "SELECT  fad "
                + " FROM  FundraiserActivityDonation fad "
                + " WHERE fad.fundraiserActivity.id = :fundraiserActivityId "
                + " and fad.donation.donationFailedInd='N' "                
                + " ORDER BY fad.donation.donationDatetime  DESC"),

        @NamedQuery(name = "getOfflineCharityTrusteeStatus",
                query = "SELECT cor.trusteeStatus.code "
                + " FROM CharityOfflineRegistration cor  "
                + " WHERE cor.charity.id = :charityId"),

        @NamedQuery(name = "getTrusteeCount",
                query = "SELECT ch.trusteeCount "
                + " FROM Charity ch "
                + " WHERE ch.id = :charityId"),

        @NamedQuery(name = "saveTrsuteeCount", query = "UPDATE Charity ch "
                + " SET ch.trusteeCount = :trusteeCount, "
                + " ch.updatedUser = :updatedUser, "
                + " ch.updatedProcess = :updatedProcess, "
                + " ch.updatedIPAddress = :updatedIPAddress "
                + " WHERE ch.id = :charityId"),
        @NamedQuery(name = "saveTrusteeStatus", query = "UPDATE CharityOfflineRegistration cor "
                + " SET cor.trusteeStatus.code = :trusteeStatusCode, "
                + " cor.updatedUser = :updatedUser, "
                + " cor.updatedProcess = :updatedProcess, "
                + " cor.updatedIPAddress = :updatedIPAddress "
                + " WHERE cor.charity.id = :charityId"),
        @NamedQuery(name = "fetchRegisteredUsersOfCharityById",
                query = "SELECT u.id, p.forename, p.surname, p.title, u.loginEmailAddress, p.dobDay, "
                + " p.dobMonth, p.dobYear "
                + " FROM CharityAdministrator charityAdmin, UserRole uRole, User u, Person p "
                + " WHERE  charityAdmin.userRole = uRole.id "
                + " AND uRole.user =  u.id "
                + " AND u.person = p.id "
                + " AND charityAdmin.charity.id = :charityId "
                + " AND uRole.role.code IN (:roleCode) "
                + " AND (uRole.endDatetime IS NULL OR uRole.endDatetime > CURRENT_TIMESTAMP) "
                + " ORDER BY p.surname, p.forename"),
        @NamedQuery(name = "fetchEmailAddresssForSettlement",
                query = "Select distinct(ea.emailAddress), p.forename, p.surname FROM "
                + " AlertTrigger at JOIN at.alertUsers au "
                + " JOIN au.charityAdministrator ca "
                + " JOIN ca.charity c "
                + " JOIN ca.userRole ur "
                + " JOIN ur.user u "
                + " JOIN u.person p "
                + " JOIN p.personEmailAddresses pea "
                + " JOIN pea.emailAddress ea "
                + " WHERE  c.id IN (:charityIds)"
                + " AND at.alertType.code = 'VMG_PAY' "),
        @NamedQuery(name = "fetchCharityPages",
                query = "SELECT page "
                + " FROM Page page "
                + " WHERE page.charity.id = :charityId "),
        @NamedQuery(name = "updateInvitedEmailAddressWithFundraiserIdQuery",
                query = "UPDATE FundraiserGroupMember f "
                + " SET f.fundraiser.id=:fundraiserId , "
                + " f.invitedEmailAddress=NULL, "
                + " f.updatedIPAddress = :updatedIPAddress, "
                + " f.updatedProcess = :updatedProcess, "
                + " f.updatedUser = :updatedUser "
                + " WHERE f.invitedEmailAddress LIKE :emailAddress"),
        @NamedQuery(name = "fetchUserRolesByUserId",
                query = "SELECT uRole "
                + " FROM UserRole uRole "
                + " WHERE uRole.user.id = :userId "),
        @NamedQuery(name = "getUserAccessRWCount",
                query = "SELECT count(uag.user.id) "
                + " FROM UserAuthGroup uag, UserRole ur "
                + " WHERE uag.user.id=ur.user.id "
                + " AND uag.authGroup.code = 'GROUP_USER_ACCESS_CREATE' "
                + " AND (ur.role.code='ROLE_O_AD' OR ur.role.code='ROLE_O_US')"),
        @NamedQuery(name = "dateStampRegularDonationForProcessed",
                query = "UPDATE RegularDonation r "
                + " SET r.updatedDateTime = current_timestamp "
                + " WHERE r.donor.id =:donorId"),

        @NamedQuery(name = "fetchCharitableActivityByCharityIdAllUsersInd",
                query = "SELECT cA from CharitableActivity cA "
                + "WHERE cA.charity.id = :charityId "
                + "AND cA.allUsersInd = 'Y'"),

        @NamedQuery(name = "fetchCharityAdministratorByUserId",
                query = "SELECT ca "
                + " FROM CharityAdministrator ca "
                + " JOIN ca.userRole ur "
                + " JOIN ur.user u "
                + " WHERE u.id = :userId "
                + " AND ur.role.code = :roleCode "
                + " AND ca.charity.id = :charityId"),

        @NamedQuery(name = "fetchCharityEventAdministratorByCharitableActivity",
                query = "SELECT ca "
                + " FROM CharityEventAdministrator ca "
                + " WHERE ca.charitableActivity.id = :charitableActivityId "
                + " AND ca.charityAdministrator.id = :charityAdminId"),

        @NamedQuery(name = "deleteCharityEventAdministrator",
                query = "DELETE "
                + " FROM CharityEventAdministrator ca "
                + " WHERE ca.charityAdministrator.id = :charityAdminId"),

        @NamedQuery(name = "deleteEventActivity",
                query = "DELETE "
                + " FROM EventActivity ea "
                + " WHERE ea.event.id = :eventId"),

        @NamedQuery(name = "fetchPageWidgetTypeFromPageType",
                query = "SELECT pwt from PageWidgetType pwt, PageType pt, AllowablePageWidget apw  "
                 + "WHERE apw.pageType.code = :pageTypeCode "
                 + "AND apw.pageType.code = pt.code "
                 + "AND apw.pageWidgetType.code = pwt.code"),
                 @NamedQuery(name = "fetchFundraiserPageForEvent", query = "SELECT p from Page p "
     				+ "WHERE p.fundraiserActivity.id =:fundraiserActivityId "
     				+ "AND p.fundraiser.id =:fundraiserId "
     				+ "AND p.event.id =:eventId"),
        @NamedQuery(name = "fetchFundraiserURLByFundraiserActivityId",
                query = "SELECT p.url, u.url "
                + " FROM Page p, UrlDetails u, Fundraiser f, FundraiserActivity fa "
                + " WHERE fa.id = :fundraiserActivityId AND fa.fundraiser.id = f.id "
                + " AND fa.id = p.fundraiserActivity.id AND f.urlDetails.id = u.id "),
        @NamedQuery(name = "fetchPagesBasedOnEventId",
                query = "SELECT pa from Page pa where "
        		+ " pa.event.id=:eventId and pa.pageStatus.code='ACT'"),
        @NamedQuery(name = "fetchLimitedPagesBasedOnEventId",
                        query = "SELECT pa from Page pa where "
                		+ " pa.event.id=:eventId and pa.pageStatus.code='ACT'"
                		+ " order by pa.id desc"),                		
        @NamedQuery(name = "fetchAlertTriggersForCharity",
                query = "FROM AlertTrigger alertTrigger "
                + " WHERE alertTrigger.charity.id = :charityId"),
                
        @NamedQuery(name = "fetchCountOfDonationForCharityForSpecifiedPeriod",
                query = "SELECT count(donation) FROM Donation donation "
                + " WHERE donation.charity.id = :charityId "
                + " AND donation.donor.id = :donorId "
                + " AND donationDatetime BETWEEN :fromDate AND :toDate"
                + " AND donation.regularDonation.id IS NULL"),

        @NamedQuery(name = "fetchCountOfDonationForCharity",
                query = "SELECT count(donation) FROM Donation donation "
                + " WHERE donation.charity.id = :charityId "
                + " AND donation.donor.id = :donorId "
                + " AND donation.regularDonation.id IS NULL"),

        @NamedQuery(name = "fetchSumOfDonationForCharityForSpecifiedPeriod",
                query = "SELECT sum(donation.grossAmount) FROM Donation donation "
                    + " WHERE donation.charity.id = :charityId "
                    + " AND donation.donor.id = :donorId "
                    + " AND donationDatetime BETWEEN :fromDate AND :toDate"),

        @NamedQuery(name = "fetchSumOfDonationForCharity",
                query = "SELECT sum(donation.grossAmount) FROM Donation donation "
                    + " WHERE donation.charity.id = :charityId "
                    + " AND donation.donor.id = :donorId "),

        @NamedQuery(name = "fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod",
                query = "SELECT count(page) FROM Page page "
                 + " WHERE page.fundraiser.id = :fundraiserId "
                 + " AND createdDateTime BETWEEN :fromDate AND :toDate"),

        @NamedQuery(name = "fetchCountOfFundraiserPagesForCharity",
                query = "SELECT count(page) FROM Page page "
                 + " WHERE page.fundraiser.id = :fundraiserId "),
                            
        @NamedQuery(name = "fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod",
                 query = "SELECT sum(donation.grossAmount) FROM Donation donation "
                 + " WHERE donation.fundraiserActivity.id = :fundraiserActivityId "
                 + " AND donationDatetime BETWEEN :fromDate AND :toDate"),

       @NamedQuery(name = "fetchSumOfFundraiserDonationForCharity",
                query = "SELECT sum(donation.grossAmount) FROM Donation donation "
                + " WHERE donation.fundraiserActivity.id = :fundraiserActivityId "),

       @NamedQuery(name = "fetchFundraiserDonationAmountForSingleEvent",
                 query = "SELECT sum(donation.grossAmount) FROM Donation donation "
                 + " WHERE donation.fundraiserActivity.id = :fundraiserActivityId "),
                 
       @NamedQuery(name = "fetchEmailAddressesForAlert",
                 query = "SELECT distinct(ea.emailAddress) "
                 + " FROM AlertUser au "
                 + " JOIN au.alertTrigger at "
                 + " JOIN au.charityAdministrator ca "
                 + " JOIN ca.userRole ur "
                 + " JOIN ur.user u "
                 + " JOIN u.person p "
                 + " JOIN p.personEmailAddresses pea "
                 + " JOIN pea.emailAddress ea "
                 + " WHERE at.id = :alertTriggerId"),

       @NamedQuery(name = "getCharityReferenceValueForDonor",
                 query = "SELECT cfv.fieldValue "
                 + " FROM CustomFieldValue cfv "
                 + " JOIN cfv.charityCustomField ccf "
                 + " WHERE ccf.charity.id = :charityId "
                 + " AND ccf.customFieldType.code = 'DON'"
                 + " AND cfv.donor.id = :donorId"),

       @NamedQuery(name = "getCharityReferenceValueForFundraiser",
                 query = "SELECT cfv.fieldValue "
                 + " FROM CustomFieldValue cfv "
                 + " JOIN cfv.charityCustomField ccf "
                 + " WHERE ccf.charity.id = :charityId "
                 + " AND ccf.customFieldType.code = 'FUN'"
                 + " AND cfv.fundraiser.id = :fundraiserId"),

       @NamedQuery(name="updateCharityAmendmentStatus",
               query = "UPDATE Charity c "
                       + " SET c.charityUpdateInd = :newStatus "
                       + " WHERE c.id = :charityId"),

       @NamedQuery(name="updateAddressAmendmentStatus",
               query = "UPDATE Address a "
                       + " SET a.addressUpdateInd = :newStatus "
                       + " WHERE a.id = :addressId"),
                       
       @NamedQuery(name="updatePageById",
               query = "UPDATE Page p "
                       + " SET p.pageStatus.code = :pageStatus "
                       + " WHERE p.id = :pageId"),
                       
       @NamedQuery(name="fetchLastCharityOfflineStatusList",
               query = "SELECT cofl "
                       + " FROM CharityOfflineRegistrationLog cofl "
                       + " WHERE cofl.createdDateTime IN ("    
                       + " SELECT MAX(cofl.createdDateTime) "
                       + " FROM CharityOfflineRegistrationLog cofl  "
                       + " JOIN cofl.offlineRegModule orm "
                       + " JOIN orm.module mo "
                       + " JOIN cofl.charityOfflineRegistration cor "
                       + " WHERE cor.id = :charityOfflineRegistrationId " 
                       + " GROUP BY mo.code) "),
       @NamedQuery(name="fetchDonationNotificationIndFromActivityId",
               query = "SELECT fa.donationNotificationInd "
                       + " FROM FundraiserActivity fa "
                       + " WHERE fa.id  = :fundraiserActivityId "),           
              
       @NamedQuery(name = "fetchTotalFundraiserDonationForCharityForSpecifiedPeriod",
               query = "SELECT sum((donation.grossAmount * frcs.splitPercentage)/100) FROM Donation donation"
                     + "  JOIN donation.fundraiserActivity fra "
                     + "  JOIN fra.fundraiser fr "
                     + "  JOIN fra.fundraisingCharitySplit frcs "
                     + "  JOIN frcs.charity charity "
                     + " WHERE fr.id = :fundRaiserId "
                     + "   AND charity.id = :charityId "
                     + "   AND donation.donationDatetime BETWEEN :fromDate AND :toDate"),

       @NamedQuery(name = "fetchTotalFundraiserDonationForCharity",
               query = "SELECT sum((donation.grossAmount * frcs.splitPercentage)/100) FROM Donation donation"
                     + "  JOIN donation.fundraiserActivity fra "
                     + "  JOIN fra.fundraiser fr "
                     + "  JOIN fra.fundraisingCharitySplit frcs "
                     + "  JOIN frcs.charity charity "
                     + " WHERE fr.id = :fundRaiserId "
                     + "   AND charity.id = :charityId "),

       @NamedQuery(name = "fetchTotalFundraiserDonationAmountForSingleCharityEvent",
               query = "SELECT sum((donation.grossAmount * frcs.splitPercentage)/100) FROM Donation donation "
                       + "  JOIN donation.fundraiserActivity fra "
                       + "  JOIN fra.fundraisingCharitySplit frcs "
                       + "  JOIN frcs.charity charity "
                       + " WHERE fra.id = :fundRaiserActivityId "
                       + "   AND charity.id = :charityId "),

       @NamedQuery(name="updateBankAccountAmendmentStatus",
               query = "UPDATE BankAccount ba "
                       + " SET ba.bankAccountUpdateInd = :newStatus "
                       + " WHERE ba.id = :bankAccountId"),        
       @NamedQuery(name="fetchFundraiserActivityForMemberOfTeam",
               query = "SELECT distinct fa "
                       + " FROM FundraiserActivity fa  "
                       + " JOIN fa.fundraiserGroup fg "
                       + " JOIN fg.fundraiserGroupType fgt "
                       + " JOIN fg.groupMembers gm "
                       + " JOIN gm.fundraiser f "
                       + " WHERE f.id  = :fundraiserId "    
                       + " AND fg.fundraiserGroupType.code = 'TEAM' ")                       
})
@Entity
public class NamedQueryRepository extends AuditAttributes implements
        Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 7445352700087130446L;
    
    /** ID value. */
    @Id
    private Long id;

    /**
     * Gets the id.
     * 
     * @return the id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
